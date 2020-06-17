package it.unipv.ingsw.progettoe20.server.database.dao;

import it.unipv.ingsw.progettoe20.ErrorStrings;
import it.unipv.ingsw.progettoe20.server.Logger;
import it.unipv.ingsw.progettoe20.server.database.DBConstants;
import it.unipv.ingsw.progettoe20.server.database.Queries;
import it.unipv.ingsw.progettoe20.server.model.Level;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.datasource.pooled.PooledDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LevelDao implements Dao<Level> {
    private PooledDataSource connectionPool;

    public LevelDao(PooledDataSource connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public Level get(String name) {
        Level level;
        try (Connection connection = connectionPool.getConnection();
             Statement stmt = connection.createStatement();
             PreparedStatement pstmt = connection.prepareStatement(Queries.LEVEL_GET);) {

            if (!checkLevelByName(name)) {
                throw new IllegalArgumentException(ErrorStrings.LEVEL_NOT_FOUND);
            }

            pstmt.setString(1, name.toUpperCase());
            ResultSet result = pstmt.executeQuery();

            result.next();
            int available = result.getInt(DBConstants.LEVEL_SECOND_COLUMN);
            int total = result.getInt(DBConstants.LEVEL_THIRD_COLUMN);
            level = new Level(name, available, total);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return level;
    }

    @Override
    public List<Level> getAll() {
        Level level;
        List<Level> levelList = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
             Statement stmt = connection.createStatement();
             PreparedStatement pstmt = connection.prepareStatement(Queries.LEVEL_GET_LIST);) {

            ResultSet result = pstmt.executeQuery();
            while (result.next()) {
                String name = result.getString(DBConstants.LEVEL_FIRST_COLUMN);
                int available = result.getInt(DBConstants.LEVEL_SECOND_COLUMN);
                int total = result.getInt(DBConstants.LEVEL_THIRD_COLUMN);
                level = new Level(name, available, total);
                levelList.add(level);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return levelList;
    }

    @Override
    public void update(Level level) {


        try (Connection connection = connectionPool.getConnection();
             Statement stmt = connection.createStatement();) {

            if (checkLevelByName(level.getName())) {
                try (PreparedStatement pstmt = connection.prepareStatement(Queries.LEVEL_UPDATE);) {

                    pstmt.setInt(1, level.getAvailable());
                    pstmt.setInt(2, level.getTotal());
                    pstmt.setString(3, level.getName());
                    pstmt.executeUpdate();
                    Logger.log("Level" + level.getName() + " updated successfully");
                }

            } else {
                try (PreparedStatement pstmt = connection.prepareStatement(Queries.LEVEL_NEW);) {
                    pstmt.setString(1, level.getName());
                    pstmt.setInt(2, level.getAvailable());
                    pstmt.setInt(3, level.getTotal());
                    pstmt.executeUpdate();
                    Logger.log("Level " + level.getName() + " created successfully");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Level level) {
        try (Connection connection = connectionPool.getConnection();
             Statement stmt = connection.createStatement();
             PreparedStatement pstmt = connection.prepareStatement(Queries.LEVEL_REMOVE);) {

            if (!checkLevelByName(level.getName())) {
                throw new IllegalArgumentException(ErrorStrings.LEVEL_NOT_FOUND);
            }

            pstmt.setString(1, level.getName());
            pstmt.executeUpdate();
            Logger.log(level.getName() + " removed from database");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Controlla che un livello sia presente nella table. Se non è presente lancia
     * un'eccezione.
     *
     * @param name identificatore del record.
     * @return booleano di verifica.
     */
    public boolean checkLevelByName(String name) throws IllegalArgumentException {

        try (Connection connection = connectionPool.getConnection();
             Statement stmt = connection.createStatement();
             PreparedStatement pstmt = connection.prepareStatement(Queries.LEVEL_GET);) {

            pstmt.setString(1, name.toUpperCase());
            ResultSet result = pstmt.executeQuery();
            if (!result.next()) {
                return false;
            }
            //TODO STAMPA LE CONNESSIONE ATTIVE DA TOGLIERE (TEST USE)
            System.out.println("active---" + connectionPool.getPoolState().getActiveConnectionCount());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
