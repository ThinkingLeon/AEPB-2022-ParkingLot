package com.example.AEPB;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ParkingLotTest {

    /**
     * 停车
     * 1. <停车场有足够空位>
     * Given  <停车场剩余十个停车位>
     * When  <用户停入一辆车>
     * Then   <停车成功，给用户含有车牌号和停车时间的停车票>
     * 2. <停车场没有足够空位>
     * Given  <停车场剩余一个停车位>
     * When  <用户停入两辆车>
     * Then   <只有一辆车停车成功，给用户含有停车成功车辆信息的停车票>
     * 3. <停车场已满>
     * Given  <停车场没有剩余车位>
     * When  <用户停入一辆车>
     * Then   <停车失败，不生成停车票>
     */
    @Test
    public void should_park_success_when_park_one_car_given_ten_unused_stall() {
        ParkingLot parkingLot = new ParkingLot(100, 90, 10);
        Car car = parkingLot.park(new Car("京A10086"));
        Assertions.assertTrue(car.isPark());
        Assertions.assertNotNull(car.getParkDate());
    }

    @Test
    public void should_one_car_park_success_and_other_fail_when_park_two_car_given_ten_one_unused_stall() {
        ParkingLot parkingLot = new ParkingLot(100, 99, 1);
        Car car = parkingLot.park(new Car("京A10086"));
        Assertions.assertTrue(car.isPark());
        Assertions.assertNotNull(car.getParkDate());
        Car car2 = parkingLot.park(new Car("京A10087"));
        Assertions.assertFalse(car2.isPark());
        Assertions.assertNull(car2.getParkDate());
    }

    @Test
    public void should_park_success_when_park_one_car_given_zero_unused_stall() {
        ParkingLot parkingLot = new ParkingLot(100, 100, 0);
        Car car = parkingLot.park(new Car("京A10086"));
        Assertions.assertFalse(car.isPark());
        Assertions.assertNull(car.getParkDate());
    }


    /**
     * 取车
     * 1. <一张停车票与车辆信息相符>
     * Given  <停车场内有两辆车>
     * When  <用户持有信息正确的一张停车票取车>
     * Then   <取出一辆对应车辆，销毁停车票>
     * 2. <多张停车票与车辆信息相符>
     * Given  <停车场内有两辆车>
     * When  <用户持有信息正确的两张停车票取车>
     * Then   <取出两辆对应车辆，销毁停车票>
     * 3. <停车票与车辆信息不相符>
     * Given  <停车场内有两辆车>
     * When  <用户持有信息错误的停车票取车>
     * Then   <取车失败>
     * 4. <用户没有停车票>
     * Given  <停车场内有两辆车>
     * When  <用户没有停车票>
     * Then   <取车失败>
     */
    @Test
    public void should_take_success_when_take_one_car_given_two_cars_have_parked() {
        ParkingLot parkingLot = prepareParkingLot();

        Ticket ticket = new Ticket("京A10086");
        boolean isTake = parkingLot.take(ticket);
        Assertions.assertTrue(isTake);
        Assertions.assertFalse(parkingLot.isExistSpecifiedCar(ticket.getUniqueFlag()));
    }



    @Test
    public void should_take_success_when_take_two_car_given_two_cars_have_parked() {
        ParkingLot parkingLot = prepareParkingLot();

        Ticket ticket = new Ticket("京A10086");
        Ticket ticket2 = new Ticket("京A10087");


        boolean isTake = parkingLot.take(ticket);
        boolean isTake2 = parkingLot.take(ticket2);
        Assertions.assertTrue(isTake);
        Assertions.assertFalse(parkingLot.isExistSpecifiedCar(ticket.getUniqueFlag()));
        Assertions.assertTrue(isTake2);
        Assertions.assertFalse(parkingLot.isExistSpecifiedCar(ticket.getUniqueFlag()));
    }

    @Test
    public void should_take_fail_when_take_one_car_given_two_cars_have_parked() {
        ParkingLot parkingLot = prepareParkingLot();

        Ticket ticket = new Ticket("京A10088");
        boolean isTake = parkingLot.take(ticket);
        Assertions.assertFalse(isTake);
    }

    @Test
    public void should_take_fail_when_take_one_car_given_no_cars_park() {
        ParkingLot parkingLot = new ParkingLot(100, 0, 100);
        Ticket ticket = new Ticket("京A10088");

        boolean isTake = parkingLot.take(ticket);
        Assertions.assertFalse(isTake);
    }

    private ParkingLot prepareParkingLot() {
        ParkingLot parkingLot = new ParkingLot(100, 0, 100);
        parkingLot.park(new Car("京A10086"));
        parkingLot.park(new Car("京A10087"));
        return parkingLot;
    }
}
