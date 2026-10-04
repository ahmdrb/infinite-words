package com.ahmdrb.wordmos.data;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class WordmosDao_Impl implements WordmosDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DocumentEntity> __insertionAdapterOfDocumentEntity;

  private final EntityInsertionAdapter<NodeEntity> __insertionAdapterOfNodeEntity;

  private final EntityDeletionOrUpdateAdapter<DocumentEntity> __deletionAdapterOfDocumentEntity;

  private final EntityDeletionOrUpdateAdapter<NodeEntity> __updateAdapterOfNodeEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteNodes;

  public WordmosDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDocumentEntity = new EntityInsertionAdapter<DocumentEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `documents` (`id`,`title`,`createdAt`,`updatedAt`) VALUES (?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DocumentEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        statement.bindLong(3, entity.getCreatedAt());
        statement.bindLong(4, entity.getUpdatedAt());
      }
    };
    this.__insertionAdapterOfNodeEntity = new EntityInsertionAdapter<NodeEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `nodes` (`id`,`documentId`,`parentId`,`title`,`description`,`nodeType`,`position`,`createdAt`,`updatedAt`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final NodeEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getDocumentId());
        if (entity.getParentId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getParentId());
        }
        statement.bindString(4, entity.getTitle());
        statement.bindString(5, entity.getDescription());
        statement.bindString(6, entity.getNodeType());
        statement.bindLong(7, entity.getPosition());
        statement.bindLong(8, entity.getCreatedAt());
        statement.bindLong(9, entity.getUpdatedAt());
      }
    };
    this.__deletionAdapterOfDocumentEntity = new EntityDeletionOrUpdateAdapter<DocumentEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `documents` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DocumentEntity entity) {
        statement.bindString(1, entity.getId());
      }
    };
    this.__updateAdapterOfNodeEntity = new EntityDeletionOrUpdateAdapter<NodeEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `nodes` SET `id` = ?,`documentId` = ?,`parentId` = ?,`title` = ?,`description` = ?,`nodeType` = ?,`position` = ?,`createdAt` = ?,`updatedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final NodeEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getDocumentId());
        if (entity.getParentId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getParentId());
        }
        statement.bindString(4, entity.getTitle());
        statement.bindString(5, entity.getDescription());
        statement.bindString(6, entity.getNodeType());
        statement.bindLong(7, entity.getPosition());
        statement.bindLong(8, entity.getCreatedAt());
        statement.bindLong(9, entity.getUpdatedAt());
        statement.bindString(10, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteNodes = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM nodes WHERE documentId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertDocument(final DocumentEntity document,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfDocumentEntity.insert(document);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertNodes(final List<NodeEntity> nodes,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfNodeEntity.insert(nodes);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteDocument(final DocumentEntity document,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfDocumentEntity.handle(document);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateNode(final NodeEntity node, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfNodeEntity.handle(node);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteNodes(final String documentId, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteNodes.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, documentId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteNodes.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<DocumentEntity>> documents() {
    final String _sql = "SELECT * FROM documents ORDER BY updatedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"documents"}, new Callable<List<DocumentEntity>>() {
      @Override
      @NonNull
      public List<DocumentEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<DocumentEntity> _result = new ArrayList<DocumentEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DocumentEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new DocumentEntity(_tmpId,_tmpTitle,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<NodeEntity>> nodes(final String documentId) {
    final String _sql = "SELECT * FROM nodes WHERE documentId = ? ORDER BY position ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, documentId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"nodes"}, new Callable<List<NodeEntity>>() {
      @Override
      @NonNull
      public List<NodeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDocumentId = CursorUtil.getColumnIndexOrThrow(_cursor, "documentId");
          final int _cursorIndexOfParentId = CursorUtil.getColumnIndexOrThrow(_cursor, "parentId");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfNodeType = CursorUtil.getColumnIndexOrThrow(_cursor, "nodeType");
          final int _cursorIndexOfPosition = CursorUtil.getColumnIndexOrThrow(_cursor, "position");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<NodeEntity> _result = new ArrayList<NodeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final NodeEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpDocumentId;
            _tmpDocumentId = _cursor.getString(_cursorIndexOfDocumentId);
            final String _tmpParentId;
            if (_cursor.isNull(_cursorIndexOfParentId)) {
              _tmpParentId = null;
            } else {
              _tmpParentId = _cursor.getString(_cursorIndexOfParentId);
            }
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpNodeType;
            _tmpNodeType = _cursor.getString(_cursorIndexOfNodeType);
            final int _tmpPosition;
            _tmpPosition = _cursor.getInt(_cursorIndexOfPosition);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new NodeEntity(_tmpId,_tmpDocumentId,_tmpParentId,_tmpTitle,_tmpDescription,_tmpNodeType,_tmpPosition,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
