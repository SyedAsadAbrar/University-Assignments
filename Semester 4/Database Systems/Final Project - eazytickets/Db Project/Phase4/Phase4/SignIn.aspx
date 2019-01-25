<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="SignIn.aspx.cs" Inherits="Phase4.SignIn" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
         <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<style>
  .center_div{
    display:block;
    margin: 10% auto;
    margin-top:200px;  
    width:33%;
  
}

body{
    background-image:url("Images/ticket_bg.jpg");
}
a{
    color:white;
}
.redBorder {
    border-color: red;
}
  

.redText{
    color:red;
}
.greenText{
    color:green;
}
</style>
</head>
<body>
     <form id="form1" runat="server">
        <div class="container">   

            <div class="row">
              
              <div class="center_div text-light" >
                  <h1>Sign in</h1>

                  <p>Email</p>
                  <asp:TextBox ID="Email" CssClass="form-control" Placeholder="Enter email" runat="server"></asp:TextBox>
                  <br/>
                  <p>Password</p>
                  <asp:TextBox ID="Password" CssClass="form-control" type="password" important="" Placeholder="Enter password" runat="server"></asp:TextBox>
                  <br/>
                  <asp:Button ID="LogIn" CssClass="btn btn-warning btn-block btn-lg" runat="server" Text="Sign in" OnClick="LogInButton_Click"/>
                  <br/>
                  <a href="SignUp.aspx">Create Account</a>
                  <br/>
                  <br/>
                  <asp:Label ID="Message" runat="server" ></asp:Label>
              </div>
             
            </div>
        </div>
    </form>
</body>
</html>
