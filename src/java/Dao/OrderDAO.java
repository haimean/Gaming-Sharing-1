/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import Model.Order;
import Model.Product;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public interface OrderDAO extends BaseDao<Order>{
    public int getProfitFromDate(java.sql.Date date);
    public ArrayList<Order> getPendingCancelOrderID();
    public int getUserID(int order_id);
    public Product getProductbyID(int order_id);
}
