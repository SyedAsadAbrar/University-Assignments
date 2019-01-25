<%@ Page Language="C#"  AutoEventWireup="true" CodeBehind="SignUp.aspx.cs" Inherits="Phase4.SignUp" %>

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
     margin-top:100px;
    width:33%;
  
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
body{
    background-image:url("Images/ticket_bg.jpg");
}

</style>
<script>
    function valueInsert(ID) {
       
        ID.style.borderColor = "lightgrey";
        
    }
</script>
</head>
<body >
     <form id="form1" runat="server">
        <div class="container">   

            <div class="row">
              
              <div class="center_div text-light" >
                  <h1>Sign up</h1>

                  <p>Name</p>
                 
                  <asp:TextBox  ID="Fname" CssClass=" form-control" Placeholder="First" runat="server" onkeypress=" valueInsert(this)"></asp:TextBox>
                 
                  <br/>
                  <asp:TextBox  ID="Lname" CssClass=" form-control" Placeholder="Last" runat="server" onkeypress=" valueInsert(this)"></asp:TextBox>
                  <br/>
                  <p>Email</p>
                  <asp:TextBox ID="Email" CssClass="form-control" Placeholder="Email" runat="server" onkeypress=" valueInsert(this)"></asp:TextBox>
                  <br/>
                  <p>City</p>
                   <asp:TextBox ID="City"  CssClass="form-control" runat="server"></asp:TextBox>
                  <br/>
                  <p>Mobile Number</p>
                  <asp:TextBox ID="Phone" CssClass="form-control" Placeholder="Phone" runat="server" onkeypress=" valueInsert(this)"></asp:TextBox>
                  <br/>
                  <p>Gender</p>
                  <asp:DropDownList ID="Gender" CssClass="btn btn-default dropdown-toggle" runat="server">
                        <asp:ListItem Text="Select" Value="0"></asp:ListItem>
                        <asp:ListItem Text="Male" Value="M"></asp:ListItem>
                        <asp:ListItem Text="Female" Value="F"></asp:ListItem>
                        <asp:ListItem Text="Other" Value="O"></asp:ListItem>

                  </asp:DropDownList>
                  <br/><br/>
                  <p>Password</p>
                  <asp:TextBox ID="Password"  CssClass="form-control" type="password" runat="server" onkeypress=" valueInsert(this)"></asp:TextBox>
                  <br/>
                  <p>Confirm Password</p>
                  <asp:TextBox ID="ConfirmPassword" CssClass="form-control" type="password" runat="server" onkeypress=" valueInsert(this)"></asp:TextBox>
                  <br/>

                  <asp:Button ID="Sign_Up" CssClass="btn btn-warning btn-block btn-lg" runat="server" Text="Sign up" onClick=" SignUpButton_Click"/>
                  <br/>
                  <asp:Label  ID="Message" runat="server" ></asp:Label>
              </div>
             
            </div>
        </div>
    </form>
</body>
</html>