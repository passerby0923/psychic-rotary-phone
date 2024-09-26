<template>
    <div id="app" class="login-container" style="position: relative;top: -63px;">
      <el-card class="login-card">
        <h2 class="login-title">用户登录</h2>
        <el-form :model="form" :rules="rules" ref="form" class="login-form">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input
              v-model="form.password"
              type="password"
              placeholder="请输入密码"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <div class="button-group">
              <el-button type="primary" @click="login" class="login-button">登录</el-button>
              <el-button type="default" @click="regist" class="register-button">注册</el-button>
            </div>
          </el-form-item>
        </el-form>
      </el-card>
  
      <el-dialog :title="zhuce.title" :visible.sync="dialogVisible">
        <span>{{ zhuce.result }}</span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">确定</el-button>
        </span>
      </el-dialog>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        // 表单数据
        form: {
          username: '',
          password: '',
        },
        // 表单验证规则
        rules: {
          username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
          password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
        },
        // 弹窗内容
        zhuce: {
          title: '温馨提示',
          result: '',
        },
        // 弹窗显示状态
        dialogVisible: false,
      };
    },
    methods: {
      // 登录方法
      login() {
        this.$refs.form.validate((valid) => {
            if (valid) {
            fetch('http://localhost:9999/user/login', {
                method: 'POST',
                headers: {
                Accept: 'application/json',
                'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: new URLSearchParams({
                username: this.form.username,
                password: this.form.password,
                }).toString(),
            })
                .then((response) => response.json())
                .then((dt) => {
                // 登录成功
                if (dt.code === 200) {
                    const role = dt.data; // 获取角色信息
                    // 根据角色分配跳转页面
                    if (role === 'admin') {
                        location.href = 'adminDashboard';
                    } else if (role === 'resident') {
                        location.href = 'residentDashboard';
                    } else if (role === 'super_admin') {
                        location.href = '/#/SuperAdmin';
                    } else {
                        location.href = 'index';
                    }
                } else {
                    // 显示错误信息
                    this.zhuce.result = dt.message; // 使用 message
                    this.dialogVisible = true;
                }
                })
                .catch((err) => {
                console.error('请求错误:', err);
                this.zhuce.result = '请求失败，请重试。';
                this.dialogVisible = true;
                });
            }
        });
        },
      // 注册方法
      regist() {
        location.href = 'regist';
      },
    },
  };
  </script>
  
  <style scoped>
  .login-container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background-color: #f5f7fa;
  }
  .login-card {
    width: 400px;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  }
  
  .login-title {
    text-align: center;
    margin-bottom: 20px;
  }
  .login-form {
    padding: 0 20px;
  }
  .button-group {
    display: flex;
    justify-content: space-between;
  }
  .login-button {
    width: 48%;
  }
  .register-button {
    width: 48%;
    background-color: #409eff;
    color: #fff;
  }
  </style>
  