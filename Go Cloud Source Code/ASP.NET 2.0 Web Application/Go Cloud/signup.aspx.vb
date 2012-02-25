Imports System.data.sqlclient

Partial Class signup
    Inherits System.Web.UI.Page
    Dim connection As SqlConnection
    Protected Sub btnSignup_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnSignup.Click
        Try
            If Session("registered") <> "" Then
                lblError1.Text = Session("registered")
            Else
                Dim username, password, confirm_password, mac_address, email As String
                username = Trim(txtUsername.Text)
                password = Trim(txtPassword.Text)
                confirm_password = Trim(txtConfirmPassword.Text)
                mac_address = Trim(txtMacAddress.Text)
                email = Trim(txtEmail.Text)
                If username <> "" And password = confirm_password And password <> "" And email <> "" Then
                    Dim param_user, param_password, param_email, param_mac, param_join_date, param_role_id As SqlParameter
                    Dim command As New SqlCommand("insert into cloud_user values(@user_name, @password, @email_id, @mac_address, @join_date, @role_id)", connection)
                    param_user = New SqlParameter("@user_name", Data.SqlDbType.VarChar, 100)
                    param_user.Value = username
                    param_password = New SqlParameter("@password", Data.SqlDbType.VarChar, 100)
                    param_password.Value = password
                    param_email = New SqlParameter("@email_id", Data.SqlDbType.VarChar, 100)
                    param_email.Value = email
                    param_mac = New SqlParameter("@mac_address", Data.SqlDbType.VarChar, 100)
                    'param_mac.Value = System.Net.NetworkInformation.NetworkInterface.GetAllNetworkInterfaces(0).GetPhysicalAddress.ToString
                    param_mac.Value = mac_address
                    param_join_date = New SqlParameter("@join_date", Data.SqlDbType.DateTime)
                    param_join_date.Value = Now
                    param_role_id = New SqlParameter("@role_id", Data.SqlDbType.SmallInt)
                    param_role_id.Value = 2
                    command.Parameters.Add(param_user)
                    command.Parameters.Add(param_password)
                    command.Parameters.Add(param_email)
                    command.Parameters.Add(param_mac)
                    command.Parameters.Add(param_join_date)
                    command.Parameters.Add(param_role_id)
                    connection.Open()
                    command.ExecuteNonQuery()
                    connection.Close()
                    System.IO.Directory.CreateDirectory(Server.MapPath(".") & "\softwares\users\" & username)
                    txtUsername.Text = ""
                    txtPassword.Text = ""
                    txtConfirmPassword.Text = ""
                    txtEmail.Text = ""
                    txtMacAddress.Text = ""
                    lblError1.Text = "Registered successfully. You may login to your account now."
                    Session("registered") = "Registered successfully. You may login to your account now."
                Else
                    lblError1.Text = "Invalid registration info !"
                End If
            End If
        Catch ex As Exception
            lblError1.Text = "Invalid registration info !"
            'MsgBox(ex.ToString)
        End Try
    End Sub
    Protected Sub txtUsername_TextChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles txtUsername.TextChanged
        Try
            Dim username As String
            username = Trim(txtUsername.Text)
            If username <> "" Then
                Dim command As New SqlCommand("select user_name from cloud_user where user_name='" & username & "'", connection)
                Dim reader As SqlDataReader
                connection.Open()
                reader = command.ExecuteReader
                If reader.HasRows = True Then
                    lblError.Text = "Username not available !"
                Else
                    lblError.Text = ""
                End If
                reader.Close()
                connection.Close()
            End If
        Catch ex As Exception
            'MsgBox(ex.ToString)
        End Try
    End Sub

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load
        Try
            If Not Page.IsPostBack Then
                If Session("registered") <> "" Then
                    Session("registered") = ""
                End If
            End If
            connection = New SqlConnection(ConfigurationManager.ConnectionStrings("GoCloudConnection").ConnectionString)
        Catch ex As Exception
            'MsgBox(ex.ToString)
        End Try
    End Sub
End Class
