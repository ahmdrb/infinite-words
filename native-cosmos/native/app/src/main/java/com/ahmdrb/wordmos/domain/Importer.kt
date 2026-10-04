package com.ahmdrb.wordmos.domain

import com.ahmdrb.wordmos.data.NodeEntity
import java.util.UUID

/** Indentation-first parser: deeper lines become children of the nearest shallower item. */
object Importer {
    data class Parsed(val title: String, val description: String, val indent: Int)

    fun parse(documentId: String, text: String): List<NodeEntity> {
        val lines = text.replace("\r\n", "\n").replace("\r", "\n").lines()
        val parsed = lines.mapNotNull { raw ->
            val cleanedCitation = raw.replace(Regex("\\[\\[\\s*\\d+\\s*\\]\\]"), "")
            if (cleanedCitation.isBlank()) return@mapNotNull null
            val indent = cleanedCitation.takeWhile { it == ' ' || it == '\t' }.let { it.count { c -> c == '\t' } * 4 + it.count { c -> c == ' ' } }
            var body = cleanedCitation.trim()
            body = body.replace(Regex("^[-*+]\\s+"), "").replace(Regex("^\\d+(?:\\.\\d+)*[.)]\\s+"), "")
            body = body.replace(Regex("\\s+([.,;:!?])"), "$1").replace(Regex("([({\\[] )"), "$1")
            val bold = Regex("^\\*\\*(.+?)\\*\\*\\s*:?\\s*(.*)$").find(body)
            if (bold != null) Parsed(bold.groupValues[1].trim(), bold.groupValues[2].trim(), indent) else Parsed(body, "", indent)
        }
        val nodes = mutableListOf<NodeEntity>(); val stack = mutableListOf<Pair<Int,String>>()
        parsed.forEachIndexed { index, item ->
            while (stack.isNotEmpty() && stack.last().first >= item.indent) stack.removeLast()
            val parent = stack.lastOrNull()?.second
            val node = NodeEntity(UUID.randomUUID().toString(), documentId, parent, item.title, item.description, "concept", index)
            nodes += node; stack += item.indent to node.id
        }
        return nodes
    }
}
