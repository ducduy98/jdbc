package dao.impl;

import dao.TripDao;
import model.Trip;
import paging.PageRequest;
import util.MySQLConnectionUtil;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TripDaoImpl extends BasicQuery<Trip, String> implements TripDao {

//    @Override
//    public void insert(Trip trip) {
//        Connection connection = MySQLConnectionUtil.getConnetion();
//        try {
//
//            connection.setAutoCommit(false);
//            String sql = "INSERT INTO chuyenbay(MaCB, GaDi, GaDen, DoDai, GioDi, GioDen, ChiPhi) VALUES (?,?,?,?,?,?,?)";
//            PreparedStatement ps = connection.prepareStatement(sql);
//
//
//            ps.setString(1, trip.getId());
//            ps.setString(2, trip.getStart());
//            ps.setString(3, trip.getDestination());
//            ps.setLong(4, trip.getLength());
//            ps.setTime(5, Time.valueOf(trip.getStartTime()));
//            ps.setTime(6, Time.valueOf(trip.getDestinationTime()));
//            ps.setInt(7, trip.getPrice());
//
//            ps.executeUpdate();
//            connection.commit();
//
//        } catch (SQLException e) {
//            try {
//                connection.rollback();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        } finally {
//            MySQLConnectionUtil.disConnect(connection);
//        }
//    }
//
//    @Override
//    public void update(String id, Trip trip) {
//        Connection connection = MySQLConnectionUtil.getConnetion();
//        String sql = "UPDATE chuyenbay SET GaDi=?, GaDen=?,DoDai=?,GioDi=?,GioDen=?,ChiPhi=? WHERE MaCB=?";
//        try {
//            connection.setAutoCommit(false);
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, trip.getStart());
//            ps.setString(2, trip.getDestination());
//            ps.setLong(3, trip.getLength());
//            ps.setTime(4, Time.valueOf(trip.getStartTime()));
//            ps.setTime(5, Time.valueOf(trip.getDestinationTime()));
//            ps.setInt(6, trip.getPrice());
//            ps.setString(7, id);
//            ps.executeUpdate();
//            connection.commit();
//
//
//        } catch (SQLException e) {
//            try {
//                connection.rollback();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        } finally {
//            MySQLConnectionUtil.disConnect(connection);
//        }
//    }
//
//    @Override
//    public void delete(String id) {
//        Connection connection = MySQLConnectionUtil.getConnetion();
//        String sql = "DELETE FROM CHUYENBAY WHERE MaCB = ?";
//        try {
//            connection.setAutoCommit(false);
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, id);
//
//            ps.executeUpdate();
//
//            connection.commit();
//
//        } catch (SQLException e) {
//            try {
//                connection.rollback();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        } finally {
//            MySQLConnectionUtil.disConnect(connection);
//        }
//    }
//
//    @Override
//    public Trip findByID(String id) {
//        Connection connection = MySQLConnectionUtil.getConnetion();
//        String sql = "SELECT * FROM CHUYENBAY WHERE MACB=?";
//        try {
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, id);
//            ResultSet rs = ps.executeQuery();
//            Trip trip = new Trip();
//            while (rs.next()) {
//                trip = covertToTrip(rs);
//            }
//            return trip;
//
//        } catch (SQLException e) {
//            return null;
//        } finally {
//            MySQLConnectionUtil.disConnect(connection);
//        }
//    }
//
//    @Override
//    public List<Trip> findAll() {
//        Connection connection = MySQLConnectionUtil.getConnetion();
//        String sql = "SELECT * FROM CHUYENBAY";
//        try {
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            List<Trip> trips = new ArrayList<>();
//            while (rs.next()) {
//                Trip trip = covertToTrip(rs);
//                trips.add(trip);
//            }
//            return trips;
//
//        } catch (SQLException e) {
//            return null;
//        } finally {
//            MySQLConnectionUtil.disConnect(connection);
//        }
//    }
//
//    public Trip covertToTrip(ResultSet rs) throws SQLException {
//        Trip trip = new Trip();
//        trip.setId(rs.getString("MaCB"));
//        trip.setStart(rs.getString("GaDi"));
//        trip.setDestination(rs.getString("GaDen"));
//        trip.setLength(rs.getLong("DoDai"));
//        trip.setStartTime(rs.getTime("GioDi").toLocalTime());
//        trip.setDestinationTime(rs.getTime("GioDen").toLocalTime());
//        trip.setPrice(rs.getInt("ChiPhi"));
//        return trip;
//    }



    public static void main(String[] args) throws SQLException {
        TripDao tripDao = new TripDaoImpl();
//        Trip trip1 = new Trip("VN990", "HANQ", "DANQ", 2000, LocalTime.now(), LocalTime.now().plusHours(3), 1000);

//        tripDao.insert(trip1);
//        tripDao.update(trip1);
//        tripDao.delete("VN999");
        Trip trip2=new Trip();
        trip2=tripDao.findByID("VN999");
        System.out.println(trip2);
//        System.out.println(trip2);
//        tripDao.findAll(PageRequest.of(3,10)).stream().forEach(System.out::println);
//        System.out.println(tripDao.count());

    }
}
