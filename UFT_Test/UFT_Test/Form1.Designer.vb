<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()>
Partial Class viewForm
    Inherits System.Windows.Forms.Form

    'フォームがコンポーネントの一覧をクリーンアップするために dispose をオーバーライドします。
    <System.Diagnostics.DebuggerNonUserCode()>
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        Try
            If disposing AndAlso components IsNot Nothing Then
                components.Dispose()
            End If
        Finally
            MyBase.Dispose(disposing)
        End Try
    End Sub

    'Windows フォーム デザイナーで必要です。
    Private components As System.ComponentModel.IContainer

    'メモ: 以下のプロシージャは Windows フォーム デザイナーで必要です。
    'Windows フォーム デザイナーを使用して変更できます。  
    'コード エディターを使って変更しないでください。
    <System.Diagnostics.DebuggerStepThrough()>
    Private Sub InitializeComponent()
        Me.labCode = New System.Windows.Forms.Label()
        Me.labName = New System.Windows.Forms.Label()
        Me.labBirthday = New System.Windows.Forms.Label()
        Me.txtCode = New System.Windows.Forms.TextBox()
        Me.txtName = New System.Windows.Forms.TextBox()
        Me.txtDate = New System.Windows.Forms.TextBox()
        Me.btnMeisai = New System.Windows.Forms.Button()
        Me.btnSearch = New System.Windows.Forms.Button()
        Me.DataGridView1 = New System.Windows.Forms.DataGridView()
        CType(Me.DataGridView1, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'labCode
        '
        Me.labCode.AutoSize = True
        Me.labCode.Font = New System.Drawing.Font("MS UI Gothic", 12.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(128, Byte))
        Me.labCode.Location = New System.Drawing.Point(45, 32)
        Me.labCode.Name = "labCode"
        Me.labCode.Size = New System.Drawing.Size(44, 16)
        Me.labCode.TabIndex = 0
        Me.labCode.Text = "コード"
        '
        'labName
        '
        Me.labName.AutoSize = True
        Me.labName.Font = New System.Drawing.Font("MS UI Gothic", 12.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(128, Byte))
        Me.labName.Location = New System.Drawing.Point(45, 57)
        Me.labName.Name = "labName"
        Me.labName.Size = New System.Drawing.Size(40, 16)
        Me.labName.TabIndex = 0
        Me.labName.Text = "名前"
        '
        'labBirthday
        '
        Me.labBirthday.AutoSize = True
        Me.labBirthday.Font = New System.Drawing.Font("MS UI Gothic", 12.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(128, Byte))
        Me.labBirthday.Location = New System.Drawing.Point(45, 83)
        Me.labBirthday.Name = "labBirthday"
        Me.labBirthday.Size = New System.Drawing.Size(72, 16)
        Me.labBirthday.TabIndex = 0
        Me.labBirthday.Text = "生年月日"
        '
        'txtCode
        '
        Me.txtCode.BackColor = System.Drawing.SystemColors.InactiveCaption
        Me.txtCode.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle
        Me.txtCode.ForeColor = System.Drawing.SystemColors.MenuHighlight
        Me.txtCode.Location = New System.Drawing.Point(158, 32)
        Me.txtCode.Name = "txtCode"
        Me.txtCode.Size = New System.Drawing.Size(171, 19)
        Me.txtCode.TabIndex = 1
        '
        'txtName
        '
        Me.txtName.BackColor = System.Drawing.SystemColors.InactiveCaption
        Me.txtName.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle
        Me.txtName.Location = New System.Drawing.Point(158, 57)
        Me.txtName.Name = "txtName"
        Me.txtName.Size = New System.Drawing.Size(171, 19)
        Me.txtName.TabIndex = 1
        '
        'txtDate
        '
        Me.txtDate.BackColor = System.Drawing.SystemColors.InactiveCaption
        Me.txtDate.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle
        Me.txtDate.Location = New System.Drawing.Point(158, 84)
        Me.txtDate.Name = "txtDate"
        Me.txtDate.Size = New System.Drawing.Size(171, 19)
        Me.txtDate.TabIndex = 1
        '
        'btnMeisai
        '
        Me.btnMeisai.Location = New System.Drawing.Point(549, 84)
        Me.btnMeisai.Name = "btnMeisai"
        Me.btnMeisai.Size = New System.Drawing.Size(75, 23)
        Me.btnMeisai.TabIndex = 2
        Me.btnMeisai.Text = "明細"
        Me.btnMeisai.UseVisualStyleBackColor = True
        '
        'btnSearch
        '
        Me.btnSearch.Location = New System.Drawing.Point(452, 84)
        Me.btnSearch.Name = "btnSearch"
        Me.btnSearch.Size = New System.Drawing.Size(75, 23)
        Me.btnSearch.TabIndex = 2
        Me.btnSearch.Text = "検索"
        Me.btnSearch.UseVisualStyleBackColor = True
        '
        'DataGridView1
        '
        Me.DataGridView1.AllowUserToAddRows = False
        Me.DataGridView1.AllowUserToDeleteRows = False
        Me.DataGridView1.AllowUserToResizeColumns = False
        Me.DataGridView1.AllowUserToResizeRows = False
        Me.DataGridView1.CellBorderStyle = System.Windows.Forms.DataGridViewCellBorderStyle.None
        Me.DataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize
        Me.DataGridView1.Location = New System.Drawing.Point(48, 156)
        Me.DataGridView1.Name = "DataGridView1"
        Me.DataGridView1.RowTemplate.Height = 21
        Me.DataGridView1.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect
        Me.DataGridView1.Size = New System.Drawing.Size(561, 230)
        Me.DataGridView1.TabIndex = 3
        '
        'viewForm
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 12.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(669, 417)
        Me.Controls.Add(Me.DataGridView1)
        Me.Controls.Add(Me.btnMeisai)
        Me.Controls.Add(Me.btnSearch)
        Me.Controls.Add(Me.txtDate)
        Me.Controls.Add(Me.txtName)
        Me.Controls.Add(Me.txtCode)
        Me.Controls.Add(Me.labBirthday)
        Me.Controls.Add(Me.labName)
        Me.Controls.Add(Me.labCode)
        Me.Name = "viewForm"
        Me.Text = "一覧画面"
        CType(Me.DataGridView1, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub

    Friend WithEvents labCode As Label
    Friend WithEvents labName As Label
    Friend WithEvents labBirthday As Label
    Friend WithEvents txtCode As TextBox
    Friend WithEvents txtName As TextBox
    Friend WithEvents txtDate As TextBox
    Friend WithEvents btnMeisai As Button
    Friend WithEvents btnSearch As Button
    Friend WithEvents DataGridView1 As DataGridView
End Class
