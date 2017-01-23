<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Bluesoft</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/dataTables.bootstrap.min.css" rel="stylesheet">

</head>

<body>
    <form action="upload" method="POST" enctype="multipart/form-data">
        <input type="file" id="file" name="file" class="filestyle" data-input="false">

        <button type="submit" class="btn btn-primary">Submit</button>
    </form>


        <c:if test="${not empty messageError}">
            <div class="alert alert-danger" role="alert">${messageError}</div>
        </c:if>
        <c:if test="${not empty messageSuccess}">
            <div class="alert alert-success" role="alert">${messageSuccess}</div>
        </c:if>

    <div class = "container">
    <h1> Data Table </h1>

    <table id="mydata" class = "table table-striped table-bordered table-hover" >
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
        </tr>
        </thead>
        <tbody>


        <c:forEach var="systemContract" items="${listSystems}">
            <tr class="myrow"  >
                <td onclick="editItem(${systemContract.id})" ><c:out value="${systemContract.id}"/></td>
                <td onclick="editItem(${systemContract.id})"><c:out value="${systemContract.system.name}"/></td>
                <td onclick="editItem(${systemContract.id})" ><c:out value="${systemContract.request}"/></td>
                <td onclick="editItem(${systemContract.id})"><c:out value="${systemContract.orderNumber}"/></td>
                <td onclick="editItem(${systemContract.id})"><c:out value="${systemContract.fromDate}"/></td>
                <td onclick="editItem(${systemContract.id})"><c:out value="${systemContract.toDate}"/></td>
                <td onclick="editItem(${systemContract.id})"><c:out value="${systemContract.amount}"/></td>
                <td onclick="editItem(${systemContract.id})"><c:out value="${systemContract.amountType}"/></td>
                <td onclick="editItem(${systemContract.id})"><c:out value="${systemContract.amountPeriod}"/></td>
                <td onclick="editItem(${systemContract.id})" ><c:out value="${systemContract.authorizationPercent}"/></td>
                <td onclick="editItem(${systemContract.id})"><c:out value="${systemContract.active}"/></td>
                <td>    <button type="button" onclick="deleteItem(${systemContract
                .id})" class="btn btn-danger">Delete</button> </td>



            </tr>
            </c:forEach>


        </tbody>
        <tfoot>


        </tfoot>
    </table>

    <div class="row">
        <div class="col-xs-4">
            <h3>Add SystemContract</h3>
            <form:form id="dataForm" action="/systems/add" method="POST" modelAttribute="systemContract" charset='utf-8'>

                <fieldset class="form-group" >
                    <label for="id">Id</label>
                    <form:input readonly="true"  id="id" class="form-control opisTextarea" path="id" rows="4"
                                />

                </fieldset>

                <fieldset class="form-group" >
                    <label for="system.name">System Name</label>
                    <form:input required="required" id="system.name" class="form-control opisTextarea" path="system.name" rows="4"
                                 placeholder="System Name" />

                </fieldset>

                <fieldset class="form-group">
                    <label for="request">Request</label>
                    <form:input required="required" id="request" class="form-control opisTextarea" path="request" rows="4"
                                placeholder="Request" type="number"/>

                </fieldset>

                <fieldset class="form-group">
                    <label for="orderNumber">Order Number</label>
                    <form:input required="required" id="orderNumber" class="form-control opisTextarea" path="orderNumber" rows="4"
                                placeholder="Order Number" value="${orderNumber}"/>

                </fieldset>

                <fieldset class="form-group">
                    <label for="fromDate">Date From</label>
                    <form:input required="required" id="fromDate" class="form-control opisTextarea" path="fromDate" rows="4"
                                placeholder="Date From" type="date"/>

                </fieldset>
                <fieldset class="form-group">
                    <label for="toDate">Date To</label>
                    <form:input required="required" id="toDate" class="form-control opisTextarea" path="toDate" rows="4"
                                placeholder="Date To" type="date"/>

                </fieldset>

                <fieldset class="form-group">
                    <label for="amount">Amount</label>
                    <form:input required="required" id="amount" class="form-control opisTextarea" path="amount" rows="4"
                                placeholder="Amount"  type="number"/>

                </fieldset>
                <fieldset class="form-group">
                    <label for="amountType">Amount Type</label>
                    <form:input required="required" id="amountType" class="form-control opisTextarea" path="amountType" rows="4"
                                placeholder="Amount Type"/>

                </fieldset>
                <fieldset class="form-group">
                    <label for="amountPeriod">Amount Period</label>
                    <form:input required="required" id="amountPeriod" class="form-control opisTextarea" path="amountPeriod" rows="4"
                                placeholder="Amount Period"/>

                </fieldset>
                <fieldset class="form-group">
                    <label for="authorizationPercent">Authorization Percent</label>
                    <form:input required="required" id="authorizationPercent" class="form-control opisTextarea" path="authorizationPercent" rows="4"
                                placeholder="Authorization Percent"  type="number"/>

                </fieldset>

                <fieldset class="form-group">
                    <label for="data-toggle">Active</label>
                    <form:checkbox path="active" id="data-toggle" value="${true}" data-onstyle="success" checked="true"
                                  />
                </fieldset>


                <button type="submit" class="btn btn-primary pull-right">Add/Edit System</button>
            </form:form>
        </div>
    </div>


</div>

<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.dataTables.min.js"></script>
<script src="js/dataTables.bootstrap.min.js"></script>

<script type="text/javascript" src="js/bootstrap-filestyle.min.js">
$(":file").filestyle();



</script>

<script>

    function editItem(id) {
        window.location = '/edit/' + id;
        console.log("1");
        return false;
    }

    $('#mydata').dataTable(); //!!!!!!!! Jeżeli chcemy dodać sortowanie. wyszukiwanie itd w tabeli.
    function deleteItem(id) {
        if (confirm("Do you want delete this item?")) {
            window.location = '/remove/' + id;
            console.log("2");
            return false;
        }
        return false;
    }

</script>

</body>
</html>