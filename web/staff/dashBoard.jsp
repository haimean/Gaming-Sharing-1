<%-- 
    Document   : index.jsp
    Created on : 01-12-2022, 13:13:16
    Author     : haimi
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Staff Dashboard</title>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js" type="text/javascript"></script>
        <script type="text/javascript">
            google.charts.load('current', {'packages': ['corechart']});
            google.charts.setOnLoadCallback(drawChart);
            function drawChart() {

            var data = google.visualization.arrayToDataTable([
            ['Gender', 'Number'],
            ['Male', ${requestScope.male}],
            ['Female', ${requestScope.female}]
            ]);
            var options = {
            title: 'Staff Gender'
            };
            var chart = new google.visualization.PieChart(document.getElementById('piechart'));
            chart.draw(data, options);
            }
        </script>
        <style>
            .diagram{
                display: flex;
                flex-direction: row;
                flex: 1;
                width: -webkit-fill-available;
            }

            .content{
                display: flex;
                flex: 1;
                flex-direction: column;
                width: 100%;
            }

            .diagram1{
                flex: 1;
            }
            .diagram2{
                flex: 1;
            }

        </style>
    </head>
    <body>
        <jsp:include page="layout/left.jsp"></jsp:include>
            <div class="container-70 d-flex justify-content-center flex-column">
                <div class="container">
                    <div class="diagram">
                        <div class="diagram1" id="piechart" style="width: 50%; height: 99%;"></div>
                        <div class="diagram2" id="linechart" style="width: 50%; height: 99%;"><canvas id="myChart"></canvas></div> 
                    </div>
                </div>
            </div>
        <jsp:include page="layout/footer.jsp"></jsp:include>   
            <script>
                const ctx = document.getElementById('myChart');
                new Chart(ctx, {
                type: 'bar',
                        data: {
                        labels: [<c:forEach items="${requestScope.profits}" var="p">
                        '${p.date}',
            </c:forEach>],
                                datasets: [{
                                label: 'Profit(đ)',
                                        data: [<c:forEach items="${requestScope.profits}" var="p">
                                        '${p.profit}',
            </c:forEach>],
                                        borderWidth: 1
                                }]
                        },
                        options: {
                        scales: {
                        y: {
                        beginAtZero: true
                        }
                        }
                        }
                });
        </script>  
    </div>
</body>
</html>
