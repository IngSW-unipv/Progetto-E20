package it.unipv.ingsw.progettoe20.server.database.dao;

import it.unipv.ingsw.progettoe20.ErrorStrings;
import it.unipv.ingsw.progettoe20.server.Logger;
import it.unipv.ingsw.progettoe20.server.database.DBConstants;
import it.unipv.ingsw.progettoe20.server.database.Queries;
import it.unipv.ingsw.progettoe20.server.model.Ticket;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.List;

public class TicketDao implements Dao<Ticket>{
    private BasicDataSource connectionPool;

    public TicketDao(BasicDataSource connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public Ticket get(String id) throws IllegalArgumentException {
        Ticket ticket;
        try {
            if (!checkTicketById(id)) {
                throw new IllegalArgumentException(ErrorStrings.ID_NOT_FOUND);
            }
            Connection connection = connectionPool.getConnection();

            Statement stmt = connection.createStatement();
            PreparedStatement pstmt = connection.prepareStatement(Queries.TICKET_GET);
            pstmt.setString(1, id);
            ResultSet result = pstmt.executeQuery();

            result.next();
            Timestamp entranceTime = result.getTimestamp(DBConstants.TICKET_SECOND_COLUMN);
            Timestamp paymentTime = result.getTimestamp(DBConstants.TICKET_THIRD_COLUMN);
            boolean paid = result.getBoolean(DBConstants.TICKET_FOURTH_COLUMN);
            ticket = new Ticket(id, entranceTime, paymentTime, paid);

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return ticket;
    }

    @Override
    public List<Ticket> getAll() {
        //TODO
        return null;
    }

    @Override
    public void update(Ticket ticket) throws IllegalArgumentException {
        Connection connection;
        PreparedStatement pstmt;

        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            // throw some error
            return;
        }

        try {
            Statement stmt = connection.createStatement();

            if (checkTicketById(ticket.getId())) {
                pstmt = connection.prepareStatement(Queries.TICKET_UPDATE);
                pstmt.setTimestamp(1, ticket.getEntranceTime());
                pstmt.setTimestamp(2, ticket.getPaymentTime());
                pstmt.setBoolean(3, ticket.isPaid());
                pstmt.setString(4, ticket.getId());
                pstmt.executeUpdate();
                Logger.log("Ticket " + ticket.getId() + " updated successfully");
            } else {
                pstmt = connection.prepareStatement(Queries.TICKET_NEW);
                pstmt.setString(1, ticket.getId());
                pstmt.setTimestamp(2, ticket.getEntranceTime());
                pstmt.setTimestamp(3, ticket.getPaymentTime());
                pstmt.setBoolean(4, ticket.isPaid());
                pstmt.executeUpdate();
                Logger.log("Ticket " + ticket.getId() + " created successfully");
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Ticket ticket) throws IllegalArgumentException {
        try {
            if (!checkTicketById(ticket.getId())) {
                throw new IllegalArgumentException(ErrorStrings.ID_NOT_FOUND);
            }
            Connection connection = connectionPool.getConnection();

            Statement stmt = connection.createStatement();
            PreparedStatement pstmt = connection.prepareStatement(Queries.TICKET_REMOVE);
            pstmt.setString(1, ticket.getId());
            pstmt.executeUpdate();
            Logger.log(ticket.getId() + " removed from database");

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkTicketById(String id) throws IllegalArgumentException {
        Connection connection;
        try {
            connection = connectionPool.getConnection();

            PreparedStatement pstmt = connection.prepareStatement(Queries.TICKET_GET);
            pstmt.setString(1, id);
            ResultSet result = pstmt.executeQuery();
            if (!result.next()) {
                return false;
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
