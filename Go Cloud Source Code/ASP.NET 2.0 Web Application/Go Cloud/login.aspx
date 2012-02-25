<%@ Page Language="VB" AutoEventWireup="true" CodeFile="login.aspx.vb" Inherits="_Default" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head runat="server">
        <title>GoCloud - Login Page</title>
    </head>
    <body>
        <form id="form1" runat="server">
            <div>   
                <div>
                    <h1>GO CLOUD </h1>
                    <hr />
                </div>
                <div>
                    <fieldset> 
                        <legend>Sign in</legend>                        
                        <table>
                            <tr>
                                <td>Username</td>
                                <td>
                                    <asp:TextBox ID="txtUsername" runat="server"></asp:TextBox>
                                </td>
                            </tr>                          
                            <tr>
                                <td> Password </td>
                                <td>
                                    <asp:TextBox ID="txtPassword" runat="server" TextMode="Password"></asp:TextBox>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <asp:Button ID="btnLogin" runat="server" Text="Login" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <asp:Label ID="lblError" runat="server" Font-Bold="True" ForeColor="Red"></asp:Label>
                                </td>
                            </tr>
                            <tr>    
                                <td>
                                    <a href="signup.aspx">New user? Sign up</a>
                                </td>
                            </tr>
                        </table>
                       </fieldset>
                       <hr />
                </div>        
            </div>                
        </form>
    </body>
</html>
