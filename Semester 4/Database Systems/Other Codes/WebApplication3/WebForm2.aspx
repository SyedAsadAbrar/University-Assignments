<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="WebForm1.aspx.cs" Inherits="WebApplication3.WebForm1" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<script type="text/javascript">
    function ConvertfromPKR() /*Convert to other currency from PKR*/ {
        var opt = document.getElementById('Currency').value;
        var pkrstr = document.getElementById('TextBox1').value;
        var out = parseInt(opt) * parseInt(pkrstr);
        document.getElementById('Output1').value = out;
    }
    function ConvertToPKR() /*Convert to other currency from PKR*/ {
        var opt1 = document.getElementById('Currency').value;
        var currstr = document.getElementById('TextBox3').value;
        var out = parseInt(currstr) / parseInt(opt1);
        document.getElementById('Output2').value = out;
    }

    function Reset() {
        var opt2 = document.getElementById('Currency').value;
        document.getElementById('TextBox5').value = opt2;
        var name = 'Syed Asad Abrar';
        document.getElementById('TextBox6').value = name;
    }
</script>
    <style>
        body{
            font-family:@Malgun Gothic;
            font-size: 1em;
            color: purple
        }
        h1{
            font-family:@Microsoft JhengHei;
            color:blue;
            font-size: 60px;
        }
        .buttonCSS {
            height:50px;
            color:blue;
            border:3px solid;
            border-color:blue;
        }
        #submit{
            height:30px;
            color:blue;
            border:3px solid;
            border-color:blue;
        }
    </style>
<body>
    <form id="form1" runat="server">
        <div>
            <h1>Currency Converting Web Site</h1>
            <br />
            <br />

            Select Currency Medium:
            &nbsp;
            &nbsp;
            &nbsp;
            <asp:DropDownList ID="Currency" runat="server">
                <asp:ListItem Text="Select" Value="0"></asp:ListItem>
                <asp:ListItem Text="Euro" Value="160"></asp:ListItem>
                <asp:ListItem Text="American Dollar" Value="110"></asp:ListItem>
                <asp:ListItem Text="British Pound Sterling" Value="180"></asp:ListItem>
            </asp:DropDownList>

            <br />
            <br />

            Please Enter amount in Rupees to Convert to your Selected Medium:
            &nbsp;
            &nbsp;
            &nbsp;
            <asp:TextBox ID="TextBox1" runat="server"></asp:TextBox>

            <br />
            <br />

            <asp:Button ID="Button1" runat="server" OnClientClick="javascript:ConvertfromPKR();" Text="Convert Rupees to Selected Medium" />

            <br />
            <br />
            
            Amount from Rupees to your chosen medium:
            &nbsp;
            &nbsp;
            &nbsp;
            <asp:TextBox ID="Output1" runat="server"></asp:TextBox>

            <br />
            <br />

            Please enter amount in your chosen medium to convert to Rupees:
            &nbsp;
            &nbsp;
            &nbsp;
            <asp:TextBox ID="TextBox3" runat="server"></asp:TextBox>

            <br />
            <br />

            <asp:Button ID="Button2" runat="server" OnClientClick="javascript:ConvertToPKR();" Text="Convert from Selected Medium to Rupees" />

            <br />
            <br />

            Amount from your chosen medium to Rupees:
            &nbsp;
            &nbsp;
            &nbsp;
            <asp:TextBox ID="Output2" runat="server"></asp:TextBox>

            <br />
            <br />

            Your Previous Chosen Medium was:
            &nbsp;
            &nbsp;
            &nbsp;
            <asp:TextBox ID="TextBox5" runat="server"></asp:TextBox>

            <br />
            <br />

            Programmed by:
            <asp:TextBox ID="TextBox6" runat="server"></asp:TextBox>

            <br />
            <br />

            <asp:Button ID="Button3" runat="server" OnClientClick="javascript:Reset();" Text="Reset" />
            <%--<asp:Button ID="Button1" CssClass="buttonCSS" runat="server" Text="Button 1" />
            <br />
            <br />
            <asp:Button ID="Button2" CssClass="buttonCSS" runat="server" Text="Button 2" />
            <br />
            <br />
            <asp:Button ID="submit" runat="server" Text="Submit" />
            <br />
            <br />--%>
        </div>
    </form>
</body>
</html>
