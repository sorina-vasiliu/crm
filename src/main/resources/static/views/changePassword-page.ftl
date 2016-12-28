<!DOCTYPE html>
<html>
<head>
    <#include "*/includes.ftl">
    <link rel="stylesheet" type="text/css" href="css/login.css">
</head>

<body>
<div class="loginContainer">
    <form id="changePassword-form" class="form-signin" action="./changepassword" method="POST">
        <h2 class="form-signin-heading">Schimba Parola</h2>
        <div class="input-group">
            <label for="password" class="control-label">Parola noua:</label>
            <input type="password" class="form-control" id="basic-url" name="password" placeholder="Parola noua" oninput="ErrorMessages(this)" oninvalid="ErrorMessages(this)" required>
        </div>
        <div class="input-group">
            <label for="confirmedPassword" class="control-label">Confirma parola:</label>
            <input type="password" class="form-control" id="basic-url" name="confirmedPassword" placeholder="Confirm parola" oninput="ErrorMessages(this)" oninvalid="ErrorMessages(this)" required>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit" style="margin-top: 3%;">Confirma</button>
        <#if error??>
            <p class="loginError">Parolele introduse nu coincid.</p>
        </#if>
    </form>
</div>
</body>
</html>