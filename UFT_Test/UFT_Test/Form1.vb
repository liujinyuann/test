Public Class viewForm
    Public dt As New DataTable
    Public ddt As New DataTable
    Public intRow As Integer

    Private Sub viewForm_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        dt.Columns.Add("コード")
        dt.Columns.Add("名前")
        dt.Columns.Add("生年月日")
        dt.Columns.Add("年齢")
        dt.Columns.Add("部門")

        Dim dr = dt.NewRow
        dr("コード") = "10001"
        dr("名前") = "中村"
        dr("生年月日") = "19900104"
        dr("年齢") = "28"
        dr("部門") = "開発一部"
        dt.Rows.Add(dr)

        dr = dt.NewRow
        dr("コード") = "10002"
        dr("名前") = "田中"
        dr("生年月日") = "19910206"
        dr("年齢") = "27"
        dr("部門") = "開発二部"
        dt.Rows.Add(dr)

        dr = dt.NewRow
        dr("コード") = "10003"
        dr("名前") = "木村"
        dr("生年月日") = "19930606"
        dr("年齢") = "25"
        dr("部門") = "管理部"
        dt.Rows.Add(dr)

        dr = dt.NewRow
        dr("コード") = "10004"
        dr("名前") = "佐々木"
        dr("生年月日") = "19920706"
        dr("年齢") = "26"
        dr("部門") = "人事部"
        dt.Rows.Add(dr)

        dr = dt.NewRow
        dr("コード") = "10005"
        dr("名前") = "佐々木2"
        dr("生年月日") = "19920706"
        dr("年齢") = "26"
        dr("部門") = "人事部"
        dt.Rows.Add(dr)

        dr = dt.NewRow
        dr("コード") = "10006"
        dr("名前") = "佐々木3"
        dr("生年月日") = "19920706"
        dr("年齢") = "26"
        dr("部門") = "人事部"
        dt.Rows.Add(dr)

        dr = dt.NewRow
        dr("コード") = "10007"
        dr("名前") = "佐々木4"
        dr("生年月日") = "19920706"
        dr("年齢") = "26"
        dr("部門") = "人事部"
        dt.Rows.Add(dr)

        dr = dt.NewRow
        dr("コード") = "10008"
        dr("名前") = "佐々木5"
        dr("生年月日") = "19920706"
        dr("年齢") = "26"
        dr("部門") = "人事部"
        dt.Rows.Add(dr)

        dr = dt.NewRow
        dr("コード") = "10009"
        dr("名前") = "佐々木6"
        dr("生年月日") = "19920706"
        dr("年齢") = "26"
        dr("部門") = "人事部"
        dt.Rows.Add(dr)

        dr = dt.NewRow
        dr("コード") = "10010"
        dr("名前") = "佐々木7"
        dr("生年月日") = "19920706"
        dr("年齢") = "26"
        dr("部門") = "人事部"
        dt.Rows.Add(dr)
		
		dr = dt.NewRow
        dr("コード") = "10010"
        dr("名前") = "佐々木8"
        dr("生年月日") = "19920706"
        dr("年齢") = "22"
        dr("部門") = "人事部"
        dt.Rows.Add(dr)

        DataGridView1.DataSource = dt
    End Sub

    Private Sub btnMeisai_Click(sender As Object, e As EventArgs) Handles btnMeisai.Click
        Dim intCnt As Integer
        intRow = 0

        For i = 0 To DataGridView1.Rows.Count - 1
            If DataGridView1.Rows(i).Selected Then
                intCnt = intCnt + 1
                intRow = i
            End If
        Next

        If intCnt > 1 Then
            MsgBox("１つだけ選択してください。")
            Exit Sub
        ElseIf intCnt < 1 Then
            MsgBox("処理対象がありません。")
            Exit Sub
        End If

        meisaiForm.Show()
        Me.Hide()
    End Sub

    Private Sub btnSearch_Click(sender As Object, e As EventArgs) Handles btnSearch.Click
        Dim strCode As String = Me.txtCode.Text
        Dim strName As String = Me.txtName.Text
        Dim strDate As String = Me.txtDate.Text
        ddt = New DataTable
        Dim ddr = ddt.NewRow
        ddt.Columns.Add("コード")
        ddt.Columns.Add("名前")
        ddt.Columns.Add("生年月日")
        ddt.Columns.Add("年齢")
        ddt.Columns.Add("部門")

        For Each rr In dt.Rows
            ddr = ddt.NewRow
            If strCode <> String.Empty AndAlso strName <> String.Empty AndAlso strDate <> String.Empty Then
                If InStr(rr("コード"), strCode) > 0 AndAlso InStr(rr("名前"), strName) > 0 AndAlso InStr(rr("生年月日"), strDate) > 0 Then
                    rowEdit(ddr, rr)
                    ddt.Rows.Add(ddr)
                End If
            ElseIf strCode <> String.Empty AndAlso strName <> String.Empty AndAlso strDate = String.Empty Then
                If InStr(rr("コード"), strCode) > 0 AndAlso InStr(rr("名前"), strName) > 0 Then
                    rowEdit(ddr, rr)
                    ddt.Rows.Add(ddr)
                End If
            ElseIf strCode <> String.Empty AndAlso strName = String.Empty AndAlso strDate <> String.Empty Then
                If InStr(rr("コード"), strCode) > 0 AndAlso InStr(rr("生年月日"), strName) > 0 Then
                    rowEdit(ddr, rr)
                    ddt.Rows.Add(ddr)
                End If
            ElseIf strCode = String.Empty AndAlso strName <> String.Empty AndAlso strDate <> String.Empty Then
                If InStr(rr("名前"), strCode) > 0 AndAlso InStr(rr("生年月日"), strName) > 0 Then
                    rowEdit(ddr, rr)
                    ddt.Rows.Add(ddr)
                End If
            ElseIf strCode <> String.Empty AndAlso strName = String.Empty AndAlso strDate = String.Empty Then
                If InStr(rr("コード"), strCode) > 0 Then
                    rowEdit(ddr, rr)
                    ddt.Rows.Add(ddr)
                End If
            ElseIf strCode = String.Empty AndAlso strName <> String.Empty AndAlso strDate = String.Empty Then
                If InStr(rr("名前"), strName) > 0 Then
                    rowEdit(ddr, rr)
                    ddt.Rows.Add(ddr)
                End If
            ElseIf strCode = String.Empty AndAlso strName = String.Empty AndAlso strDate <> String.Empty Then
                If InStr(rr("生年月日"), strDate) > 0 Then
                    rowEdit(ddr, rr)
                    ddt.Rows.Add(ddr)
                End If
            Else
                rowEdit(ddr, rr)
                ddt.Rows.Add(ddr)
            End If
        Next

        DataGridView1.DataSource = ddt
    End Sub

    Private Sub rowEdit(ByRef ddr As DataRow, ByVal rr As DataRow)
        ddr("コード") = rr("コード")
        ddr("名前") = rr("名前")
        ddr("生年月日") = rr("生年月日")
        ddr("年齢") = rr("年齢")
        ddr("部門") = rr("部門")
    End Sub

End Class
