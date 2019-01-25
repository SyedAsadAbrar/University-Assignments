<%@ Page Title="" Language="C#" MasterPageFile="~/adminLayout.Master" AutoEventWireup="true" CodeBehind="AddMovie.aspx.cs" Inherits="DatabaseProject.AddMovie" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
    <style>
        .center_div {
            display: block;
            margin: 10% auto;
            margin-top: 100px;
            width: 33%;
        }

        .redBorder {
            border-color: red;
        }


        .redText {
            color: red;
        }

        .greenText {
            color: green;
        }

        p, h1 {
            color: black;
        }

        .top-panel {
            border-bottom: 1px solid whitesmoke !important;
            padding-top: 100px;
            padding-bottom: 50px;
            padding-left: 100px;
        }

        .id {
            font-size: 25px !important;
            font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
        }

        .info, p {
            font-size: 15px !important;
            font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
        }

        .btn-file {
            position: relative;
            overflow: hidden;
        }

            .btn-file input[type=file] {
                position: absolute;
                top: 0;
                right: 0;
                min-width: 100%;
                min-height: 100%;
                font-size: 100px;
                text-align: right;
                filter: alpha(opacity=0);
                opacity: 0;
                outline: solid black;
                background: white;
                cursor: inherit;
                display: block;
            }

        #img-upload {
            width: 100%;
        }
    </style>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div class="container">
        <div class="row">
            <div class="center_div text">
                <h1>Add Movie</h1>

                <p>Movie Name</p>
                <asp:TextBox ID="movieName" CssClass=" form-control" runat="server" onkeypress=" valueInsert(this)"></asp:TextBox>
                <br />
                <p>Director Name</p>
                <asp:TextBox ID="director" CssClass=" form-control" runat="server" onkeypress=" valueInsert(this)"></asp:TextBox>
                <br />
                <p>Writer Name</p>
                <asp:TextBox ID="writer" CssClass="form-control" runat="server" onkeypress=" valueInsert(this)"></asp:TextBox>
                <br />
                <p>Running Time</p>
                <div class="container">
                    <div class="row">
                        <div class='col-sm-6'>
                            <div class="form-group">
                                <div class='input-group date' id='datetimepicker3'>
                                    <input type='text' class="form-control" />
                                    <span class="input-group-addon">
                                        <span class="glyphicon glyphicon-time"></span>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <script type="text/javascript">
                            $(function () {
                                $('#datetimepicker3').datetimepicker({
                                    format: 'LT'
                                });
                            });
                        </script>
                    </div>
                </div>
                <br />
                <p>Release Date</p>
                <asp:Calendar ID="Calendar1" runat="server"></asp:Calendar>
                <br />
                <p>Genre</p>
                <asp:TextBox ID="Genre" CssClass="form-control" type="text" runat="server" onkeypress=" valueInsert(this)"></asp:TextBox>
                <br />
                <p>Main Cast</p>
                <asp:TextBox ID="Cast" CssClass="form-control" type="text" runat="server" onkeypress=" valueInsert(this)"></asp:TextBox>
                <br />
                <p>Movie Poster</p>
                <div class="input-group">
                    <span class="input-group-btn">
                        <span class="btn btn-default btn-file">Browse…
                            <asp:FileUpload ID="FileUpload1" runat="server"></asp:FileUpload>
                        </span>
                    </span>
                    <input type="text" class="form-control" readonly>
                </div>
                <br />
                <asp:Button ID="AddImage" CssClass="btn btn-warning btn-block btn-lg" runat="server" Text="Submit" OnClick="btnsave_Click" />
                <br />
                <asp:Label ID="lblmessage" runat="server" />
            </div>
        </div>
    </div>
</asp:Content>
