Imports System.Data.SqlClient
Partial Class _Default
    Inherits System.Web.UI.Page
    Dim connection As SqlConnection
    Protected Sub btnLogin_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnLogin.Click
        Try
            Dim username, password As String
            username = Trim(txtUsername.Text)
            password = Trim(txtPassword.Text)
            Dim command As New SqlCommand("select password,role_id from cloud_user where user_name='" & username & "'", connection)
            Dim reader As SqlDataReader
            connection.Open()
            reader = command.ExecuteReader
            If (reader.Read) Then
                If password = reader.Item(0) Then
                    Session("username") = username
                    Session("role_id") = reader.Item(1)
                    Response.Redirect("home.aspx")
                Else
                    lblError.Text = "Invalid password !"
                    txtPassword.Text = ""
                End If
            Else
                lblError.Text = "Invalid username !"
                txtUsername.Text = ""
            End If
            connection.Close()
        Catch ex As Exception
            lblError.Text = ex.ToString
            'MsgBox(ex.ToString)
        End Try
    End Sub

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load
        Try
            connection = New SqlConnection(ConfigurationManager.ConnectionStrings("GoCloudConnection").ConnectionString)
        Catch ex As Exception
            lblError.Text = ex.ToString
            'MsgBox(ex.ToString)
        End Try
    End Sub
End Class