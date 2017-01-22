<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/dataTables.bootstrap.min.css" rel="stylesheet">

</head>
<body>



    <form action="upload" method="POST" enctype="multipart/form-data">
        <input type="file" id="file" name="file" class="filestyle" data-input="false">

        <button type="submit" class="btn btn-primary">Submit</button>
    </form>



<div class = "container">
    <h1> Data Table </h1>

    <table class = "table table-striped table-bordered table-hover" id="mydata">
        <thead>
        <tr>
            <th>ID</th>
            <th>System Name</th>
            <th>Request</th>
            <th>OrderNumber</th>
            <th>FromDate</th>
            <th>ToDate</th>
            <th>Amount</th>
            <th>AmountType</th>
            <th>AmountPeriod</th>
            <th>Authorization %</th>
            <th>Active</th>
            <th>Delete</th>
            <th>Edit</th>
        </tr>
        </thead>
        <tbody>

            <c:forEach var="systemContract" items="${listSystems}">
            <tr class='clickable-row' >
                <td><c:out value="${systemContract.id}"/></td>
                <td><c:out value="${systemContract.system.name}"/></td>
                <td><c:out value="${systemContract.request}"/></td>
                <td><c:out value="${systemContract.orderNumber}"/></td>
                <td><c:out value="${systemContract.fromDate}"/></td>
                <td><c:out value="${systemContract.toDate}"/></td>
                <td><c:out value="${systemContract.amount}"/></td>
                <td><c:out value="${systemContract.amountType}"/></td>
                <td><c:out value="${systemContract.amountPeriod}"/></td>
                <td><c:out value="${systemContract.authorizationPercent}"/></td>
                <td><c:out value="${systemContract.active}"/></td>
                <td>    <button type="button" onclick="deleteItem(${systemContract
                .id})" class="btn btn-danger">Delete</button> </td>

                <td>    <button type="button" onclick="editItem(${systemContract
                        .id})" class="btn  btn btn-warning">Edit</button> </td>

            </tr>
            </c:forEach>


        </tbody>
        <tfoot>


        </tfoot>
    </table>

    <div class="row">
        <div class="col-xs-4">
            <h3>Add SystemContract</h3>
            <form:form action="/systems/add" method="POST" modelAttribute="systemContract" charset='utf-8'>

                <fieldset class="form-group">
                    <label for="request">System Name</label>
                    <form:input  id="system.name" class="form-control opisTextarea" path="system.name" rows="4"
                                 placeholder="System Name"/>
                    <div class="has-error">
                        <form:errors path="system.name" class="help-inline"/>
                    </div>
                </fieldset>




                <fieldset class="form-group">
                    <label for="request">Request</label>
                    <form:input  id="request" class="form-control opisTextarea" path="request" rows="4"
                                placeholder="Request" type="number"/>
                    <div class="has-error">
                        <form:errors path="request" class="help-inline"/>
                    </div>
                </fieldset>

                <fieldset class="form-group">
                    <label for="orderNumber">Order Number</label>
                    <form:input id="orderNumber" class="form-control opisTextarea" path="orderNumber" rows="4"
                                placeholder="Order Number" value="${orderNumber}"/>
                    <div class="has-error">
                        <form:errors path="orderNumber" class="help-inline"/>
                    </div>
                </fieldset>

                <fieldset class="form-group">
                    <label for="fromDate">Date From</label>
                    <form:input id="fromDate" class="form-control opisTextarea" path="fromDate" rows="4"
                                placeholder="Date From" type="date"/>
                    <div class="has-error">
                        <form:errors path="fromDate" class="help-inline"/>
                    </div>
                </fieldset>
                <fieldset class="form-group">
                    <label for="toDate">Date To</label>
                    <form:input id="toDate" class="form-control opisTextarea" path="toDate" rows="4"
                                placeholder="Date To" type="date"/>
                    <div class="has-error">
                        <form:errors path="toDate" class="help-inline"/>
                    </div>
                </fieldset>

                <fieldset class="form-group">
                    <label for="amount">Amount</label>
                    <form:input id="amount" class="form-control opisTextarea" path="amount" rows="4"
                                placeholder="Amount"  type="number"/>
                    <div class="has-error">
                        <form:errors path="amount" class="help-inline"/>
                    </div>
                </fieldset>
                <fieldset class="form-group">
                    <label for="amountType">Amount Type</label>
                    <form:input id="amountType" class="form-control opisTextarea" path="amountType" rows="4"
                                placeholder="Amount Type"/>
                    <div class="has-error">
                        <form:errors path="amountType" class="help-inline"/>
                    </div>
                </fieldset>
                <fieldset class="form-group">
                    <label for="amountPeriod">Amount Period</label>
                    <form:input id="amountPeriod" class="form-control opisTextarea" path="amountPeriod" rows="4"
                                placeholder="Amount Period"/>
                    <div class="has-error">
                        <form:errors path="amountPeriod" class="help-inline"/>
                    </div>
                </fieldset>
                <fieldset class="form-group">
                    <label for="authorizationPercent">Authorization Percent</label>
                    <form:input id="authorizationPercent" class="form-control opisTextarea" path="authorizationPercent" rows="4"
                                placeholder="Authorization Percent"  type="number"/>
                    <div class="has-error">
                        <form:errors path="authorizationPercent" class="help-inline"/>
                    </div>
                </fieldset>

                <fieldset class="form-group">
                    <div>
                        <label for="data-toggle">Active</label>
                    </div>
                    <form:checkbox path="active" id="data-toggle" value="${true}" data-onstyle="success" checked="true"
                                   data-on="<span class='fa fa-check'></span> Activated"
                                   data-off="<span class='fa fa-times'></span> Deactivated"
                                   data-toggle="toggle"/>
                </fieldset>


                <button type="submit" class="btn btn-primary pull-right">Add System</button>
            </form:form>
        </div>
    </div>


</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.dataTables.min.js"></script>
<script src="js/dataTables.bootstrap.min.js"></script>

    <script type="text/javascript" src="js/bootstrap-filestyle.min.js">
        $(":file").filestyle();
    </script>
<script>
    $('#mydata').dataTable();

    function deleteItem(id) {
        if (confirm("Do you want delete this item?")) {
            window.location = '/remove/' + id;
        }
        return false;
    }


   




</script>

</body>
</html>