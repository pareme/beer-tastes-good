<html>
<head>
    <meta name="layout" content="public"/>
    <title>Home Page</title>
</head>
<body>

<div id="content" role="main">
    <section class="row colset-2-its">
        <h1>Welcome ${name}!</h1>

        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>

        <p>There are ${breweryTotal} beer in the database.</p>

        <ul>
            <g:each in="${breweryList}" var="brewery">
                <li>
                    <g:link controller="brewery" action="show" id="${brewery.id}">
                        ${brewery.name} - ${brewery.address.toString()}
                    </g:link>
                    <ul>
                        <g:each in="${brewery.beer}" var="beer">
                            <g:link controller="beer" action="show" id="${beer.id}">
                                <li>
                                    ${beer.name} - ${beer.type}/${beer.style}
                                </li>
                            </g:link>
                        </g:each>
                    </ul>
                </li>
            </g:each>
        </ul>

        <g:form action="updateName" style="margin: 0 auto; width:320px">
            <g:textField name="name" value=""/>
            <g:submitButton name="Update name"/>
        </g:form>

    </section>
</div>

</body>
</html>