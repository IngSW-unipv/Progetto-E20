package it.unipv.ingsw.progettoe20.server.database.dao;

import it.unipv.ingsw.progettoe20.ErrorStrings;
import it.unipv.ingsw.progettoe20.server.Logger;
import it.unipv.ingsw.progettoe20.server.database.DBConstants;
import it.unipv.ingsw.progettoe20.server.database.Queries;
import it.unipv.ingsw.progettoe20.server.model.Price;
import org.apache.ibatis.datasource.pooled.PooledDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PriceDao implements Dao<Price> {
    private PooledDataSource connectionPool;

    public PriceDao(PooledDataSource connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public Price get(String minutesText) {
        int minutes = Integer.parseInt(minutesText);
        Price price;
        try (Connection connection = connectionPool.getConnection();
             Statement stmt = connection.createStatement();
             PreparedStatement pstmt = connection.prepareStatement(Queries.LEVEL_GET)) {

            if (!checkPriceByMinutes(minutes)) {
                throw new IllegalArgumentException(ErrorStrings.PRICE_NOT_FOUND);
            }

            pstmt.setInt(1, minutes);
            ResultSet result = pstmt.executeQuery();

            result.next();
            double dbPrice = result.getDouble(DBConstants.PRICES_SECOND_COLUMN);
            price = new Price(minutes, dbPrice);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return price;
    }

    @Override
    public List<Price> getAll() {
        Price price;
        List<Price> priceList = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             Statement stmt = connection.createStatement();
             PreparedStatement pstmt = connection.prepareStatement(Queries.PRICES_GET_LIST)) {

            ResultSet result = pstmt.executeQuery();
            while (result.next()) {
                int minutes = result.getInt(DBConstants.PRICES_FIRST_COLUMN);
                double dbPrice = result.getDouble(DBConstants.PRICES_SECOND_COLUMN);
                price = new Price(minutes, dbPrice);
                priceList.add(price);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return priceList;
    }

    @Override
    public void update(Price price) {
        try (Connection connection = connectionPool.getConnection();
             Statement stmt = connection.createStatement()) {

            if (checkPriceByMinutes(price.getMinutes())) {
                try (PreparedStatement pstmt = connection.prepareStatement(Queries.PRICES_UPDATE)) {
                    pstmt.setDouble(1, price.getPrice());
                    pstmt.setInt(2, price.getMinutes());
                    pstmt.executeUpdate();
                    Logger.log("Price for " + price.getMinutes() + " minutes updated successfully");
                }

            } else {
                try (PreparedStatement pstmt = connection.prepareStatement(Queries.PRICES_NEW)) {
                    pstmt.setInt(1, price.getMinutes());
                    pstmt.setDouble(2, price.getPrice());
                    pstmt.executeUpdate();
                    Logger.log("Price for " + price.getMinutes() + " minutes created successfully");
                }


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Price price) {
        try (Connection connection = connectionPool.getConnection();
             Statement stmt = connection.createStatement();
             PreparedStatement pstmt = connection.prepareStatement(Queries.PRICES_REMOVE)) {
            if (!checkPriceByMinutes(price.getMinutes())) {
                throw new IllegalArgumentException(ErrorStrings.PRICE_NOT_FOUND);
            }

            pstmt.setInt(1, price.getMinutes());
            pstmt.executeUpdate();
            Logger.log("Price for " + price.getMinutes() + " minutes removed from database");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Controlla che una tariffa sia presente nella table. Se non è presente lancia
     * un'eccezione.
     *
     * @param minutes identificatore del record.
     * @return presenza.
     */
    public boolean checkPriceByMinutes(int minutes) throws IllegalArgumentException {

        try(Connection connection = connectionPool.getConnection();
            Statement stmt = connection.createStatement()) {

            ResultSet result = stmt.executeQuery(Queries.PRICES_GET_LIST);
            while (result.next()) {
                if (result.getInt(DBConstants.PRICES_FIRST_COLUMN) == minutes) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
