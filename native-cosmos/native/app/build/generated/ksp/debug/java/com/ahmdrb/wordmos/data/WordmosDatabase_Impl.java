package com.ahmdrb.wordmos.data;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class WordmosDatabase_Impl extends WordmosDatabase {
  private volatile WordmosDao _wordmosDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `documents` (`id` TEXT NOT NULL, `title` TEXT NOT NULL, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `nodes` (`id` TEXT NOT NULL, `documentId` TEXT NOT NULL, `parentId` TEXT, `title` TEXT NOT NULL, `description` TEXT NOT NULL, `nodeType` TEXT NOT NULL, `position` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_nodes_documentId` ON `nodes` (`documentId`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_nodes_parentId` ON `nodes` (`parentId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '3caca8e99e8db457cccc50a0eb54e107')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `documents`");
        db.execSQL("DROP TABLE IF EXISTS `nodes`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsDocuments = new HashMap<String, TableInfo.Column>(4);
        _columnsDocuments.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDocuments.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDocuments.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDocuments.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDocuments = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDocuments = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDocuments = new TableInfo("documents", _columnsDocuments, _foreignKeysDocuments, _indicesDocuments);
        final TableInfo _existingDocuments = TableInfo.read(db, "documents");
        if (!_infoDocuments.equals(_existingDocuments)) {
          return new RoomOpenHelper.ValidationResult(false, "documents(com.ahmdrb.wordmos.data.DocumentEntity).\n"
                  + " Expected:\n" + _infoDocuments + "\n"
                  + " Found:\n" + _existingDocuments);
        }
        final HashMap<String, TableInfo.Column> _columnsNodes = new HashMap<String, TableInfo.Column>(9);
        _columnsNodes.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNodes.put("documentId", new TableInfo.Column("documentId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNodes.put("parentId", new TableInfo.Column("parentId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNodes.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNodes.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNodes.put("nodeType", new TableInfo.Column("nodeType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNodes.put("position", new TableInfo.Column("position", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNodes.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNodes.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysNodes = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesNodes = new HashSet<TableInfo.Index>(2);
        _indicesNodes.add(new TableInfo.Index("index_nodes_documentId", false, Arrays.asList("documentId"), Arrays.asList("ASC")));
        _indicesNodes.add(new TableInfo.Index("index_nodes_parentId", false, Arrays.asList("parentId"), Arrays.asList("ASC")));
        final TableInfo _infoNodes = new TableInfo("nodes", _columnsNodes, _foreignKeysNodes, _indicesNodes);
        final TableInfo _existingNodes = TableInfo.read(db, "nodes");
        if (!_infoNodes.equals(_existingNodes)) {
          return new RoomOpenHelper.ValidationResult(false, "nodes(com.ahmdrb.wordmos.data.NodeEntity).\n"
                  + " Expected:\n" + _infoNodes + "\n"
                  + " Found:\n" + _existingNodes);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "3caca8e99e8db457cccc50a0eb54e107", "af3d777a96c7d39ceacab8721b3a2d31");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "documents","nodes");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `documents`");
      _db.execSQL("DELETE FROM `nodes`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(WordmosDao.class, WordmosDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public WordmosDao dao() {
    if (_wordmosDao != null) {
      return _wordmosDao;
    } else {
      synchronized(this) {
        if(_wordmosDao == null) {
          _wordmosDao = new WordmosDao_Impl(this);
        }
        return _wordmosDao;
      }
    }
  }
}
