<%@ Page Title="" Language="C#" MasterPageFile="~/Master1.Master" AutoEventWireup="true" CodeBehind="SignUp.aspx.cs" Inherits="DbProjectOld.SignUp" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
    <title></title>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="body" runat="server">
    <form id="form1" runat="server">
        <div class="container">

            <div class="row">

                <div class="center_div text-light">
                    <h1>Sign up</h1>

                    <p>Name</p>

                    <asp:TextBox ID="Fname" CssClass=" form-control" Placeholder="First" runat="server" onkeypress=" valueInsert(this)"></asp:TextBox>

                    <br />
                    <asp:TextBox ID="Lname" CssClass=" form-control" Placeholder="Last" runat="server" onkeypress=" valueInsert(this)"></asp:TextBox>
                    <br />
                    <p>Email</p>
                    <asp:TextBox ID="Email" CssClass="form-control" Placeholder="Email" runat="server" onkeypress=" valueInsert(this)"></asp:TextBox>
                    <br />
                    <p>City</p>
                    <asp:TextBox ID="City" CssClass="form-control" runat="server"></asp:TextBox>
                    <br />
                    <p>Mobile Number</p>
                    <asp:TextBox ID="Phone" CssClass="form-control" Placeholder="Phone" runat="server" onkeypress=" valueInsert(this)"></asp:TextBox>
                    <br />
                    <p>Gender</p>
                    <asp:DropDownList ID="Gender" CssClass="btn btn-default dropdown-toggle" runat="server">
                        <asp:ListItem Text="Select" Value="0"></asp:ListItem>
                        <asp:ListItem Text="Male" Value="M"></asp:ListItem>
                        <asp:ListItem Text="Female" Value="F"></asp:ListItem>
                        <asp:ListItem Text="Other" Value="O"></asp:ListItem>

                    </asp:DropDownList>
                    <br />
                    <br />
                    <p>Password</p>
                    <asp:TextBox ID="Password" CssClass="form-control" type="password" runat="server" onkeypress=" valueInsert(this)"></asp:TextBox>
                    <br />
                    <p>Confirm Password</p>
                    <asp:TextBox ID="ConfirmPassword" CssClass="form-control" type="password" runat="server" onkeypress=" valueInsert(this)"></asp:TextBox>
                    <br />

                    <asp:Button ID="Sign_Up" CssClass="btn btn-warning btn-block btn-lg" runat="server" Text="Sign up" OnClick=" SignUpButton_Click" />
                    <br />
                    <asp:Label ID="Message" runat="server"></asp:Label>
                </div>

            </div>
        </div>
    </form>
    </body>
</asp:Content>
