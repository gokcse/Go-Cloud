<%@ Page Language="VB" AutoEventWireup="false" CodeFile="signup.aspx.vb" Inherits="signup" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
    <head runat="server">
        <title>Go Cloud - Sign up for your account</title>
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
                        <legend>Sign up</legend>                        
                        <table>
                            <tr>
                                <td>Username</td>
                                <td>
                                    <asp:TextBox ID="txtUsername" runat="server" AutoPostBack="True"></asp:TextBox>
                                    <asp:Label ID="lblError" runat="server" Font-Bold="True" ForeColor="Red"></asp:Label>
                                </td>
                            </tr>                          
                            <tr>
                                <td> Password </td>
                                <td>
                                    <asp:TextBox ID="txtPassword" runat="server" TextMode="Password"></asp:TextBox>
                                </td>
                            </tr>
                            <tr>
                                <td> Confirm Password </td>
                                <td>
                                    <asp:TextBox ID="txtConfirmPassword" runat="server" TextMode="Password"></asp:TextBox>
                                </td>
                            </tr>
                            <tr>
                                <td> MAC Address </td>
                                <td>
                                    <asp:TextBox ID="txtMacAddress" runat="server"></asp:TextBox>
                                </td>
                            </tr>
                            <tr>
                                <td> Email </td>
                                <td>
                                    <asp:TextBox ID="txtEmail" runat="server" ></asp:TextBox>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <asp:Label ID="lblError1" runat="server" Font-Bold="True" ForeColor="Red"></asp:Label><br />
                                    <asp:Button ID="btnSignup" runat="server" Text="Register" />
                                </td>
                            </tr>
                            <tr>    
                                <td>
                                    <a href="login.aspx">Already have an account? Login here</a>
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
