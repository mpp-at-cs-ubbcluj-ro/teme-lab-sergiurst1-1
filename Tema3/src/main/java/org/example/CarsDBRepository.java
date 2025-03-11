package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CarsDBRepository implements CarRepository{

    private JdbcUtils dbUtils;



    private static final Logger logger= LogManager.getLogger();

    public CarsDBRepository(Properties props) {
        logger.info("Initializing CarsDBRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public List<Car> findByManufacturer(String manufacturerN) {
        logger.traceEntry("Finding All Cars by Manufacturer {}", manufacturerN);
        Connection conn = dbUtils.getConnection();
        List<Car> cars = new ArrayList<>();
        try (PreparedStatement preStat = conn.prepareStatement("SELECT * FROM cars WHERE manufacturer=?")) {
            preStat.setString(1, manufacturerN);
            try (ResultSet rs = preStat.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String manufacturer = rs.getString("manufacturer");
                    String model = rs.getString("model");
                    int year = rs.getInt("year");
                    Car car = new Car(manufacturer, model, year);
                    car.setId(id);
                    cars.add(car);
                }
            }
        } catch (Exception e) {
            logger.error(e);
            System.err.println("Error DB" + e);
        }
        logger.traceExit();

        return cars;
    }

    @Override
    public List<Car> findBetweenYears(int min, int max) {
	//to do
        return null;
    }

    @Override
    public void add(Car elem) {
        logger.traceEntry("Adding Car {}", elem);
        Connection conn=dbUtils.getConnection();

        try(PreparedStatement preStat = conn.prepareStatement("INSERT INTO cars (manufacturer, model, year) VALUES (?, ?, ?)")){

            preStat.setString(1, elem.getManufacturer());
            preStat.setString(2, elem.getModel());
            preStat.setInt(3, elem.getYear());
            int result = preStat.executeUpdate();
            logger.trace("Added Car {}", result);
        }catch(SQLException e){

            logger.error(e);
            System.err.println("Error DB" + e);
        }
        logger.traceExit();
    }

    @Override
    public void update(Integer integer, Car elem) {
      //to do
    }

    @Override
    public Iterable<Car> findAll() {
         logger.traceEntry("Finding All Cars");
         Connection conn=dbUtils.getConnection();
         List<Car> cars=new ArrayList<>();
         try(PreparedStatement preStat=conn.prepareStatement("SELECT * FROM cars")) {
             try(ResultSet rs=preStat.executeQuery()){
                 while(rs.next()){
                    int id = rs.getInt("id");
                    String manufacturer = rs.getString("manufacturer");
                    String model = rs.getString("model");
                    int year = rs.getInt("year");
                    Car car = new Car(manufacturer, model, year);
                    car.setId(id);
                    cars.add(car);
                 }
             }
         } catch (Exception e) {
            logger.error(e);
            System.err.println("Error DB" + e);
         }
         logger.traceExit();

         return cars;
    }
}
