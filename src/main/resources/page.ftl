<#-- @ftlvariable name="httpRequestInfo" type="ru.n5g.stepic.webservice.model.HttpRequestInfo" -->
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Title</title>
</head>
<body>

<p>Message: ${httpRequestInfo.message}</p>
<p>Method: ${httpRequestInfo.method}</p>
<p>URL: ${httpRequestInfo.URL}</p>
<p>PathInfo: ${httpRequestInfo.pathInfo}</p>
<p>SessionId: ${httpRequestInfo.sessionId}</p>
<p>Parameters: ${httpRequestInfo.parameters}</p>

</body>
</html>
