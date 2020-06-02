package com.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shopping.dto.OrderPerMonthChartDTO;
import com.shopping.dto.OrderTypePerMonthChartDTO;
import com.shopping.dto.ProfitPerMonthChartDTO;
import com.shopping.entity.Order;

@Repository("orderRepository")
public interface OrderRepository extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order>{
	
	@Query("FROM Order o where o.userEntity.username = ?1 order by o.id desc")
	List<Order> findAllByUsername(String username);
	
	@Query(value = "SELECT COUNT(order_id) FROM orders WHERE MONTH(date) = MONTH(CURRENT_DATE()) AND YEAR(date) = YEAR(CURRENT_DATE())", nativeQuery = true)
	 int getTotalOrderInMonth();
	
	@Query(value="SELECT MONTH(date) as month, COUNT(order_id) as total FROM orders WHERE MONTH(date)>=1 and MONTH(date)<=6 GROUP BY MONTH(date) order by month asc", nativeQuery = true)
	List<OrderPerMonthChartDTO> getOrderPerMonth();
	
	@Query(value = "SELECT MONTH(date) as month, SUM(sub_price) as total FROM orders INNER JOIN order_detail on orders.order_id=order_detail.order_id WHERE MONTH(date)>=1 and MONTH(date)<=6 AND delivery_status='COMPLETED' GROUP BY MONTH(date) ORDER BY month asc", nativeQuery = true)
	List<ProfitPerMonthChartDTO> getProfitPerMonth();
	
	@Query(value = "SELECT payment_method as type, COUNT(order_id) as total FROM orders GROUP BY payment_method ORDER BY type asc", nativeQuery = true)
	List<OrderTypePerMonthChartDTO> getOrderType();
	
	
}
