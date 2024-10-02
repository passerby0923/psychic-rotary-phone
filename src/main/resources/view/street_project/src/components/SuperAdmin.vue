<template>
    <div>
        <el-container class="move-up">
            <!-- 侧边栏 -->
            <el-aside :style="{ width: isCollapse ? '64px' : asidWidth }" style="height: 800px; background-color: #8c939d; color: white;">
                <div style="height: 60px; background-color: #8c939d; color: white; display: flex; align-items: center; justify-content: center;">
                    <img src="@/assets/4.png" alt="" style="width: 40px; height: 40px;">
                    <transition name="el-fade-in">
                        <span style="margin-left: 5px; font-size: 20px;" v-show="!isCollapse">街道管理</span>
                    </transition>
                </div>
                <el-menu
                    :collapse="isCollapse"
                    default-active="2"
                    class="el-menu-vertical-demo"
                    @open="handleOpen"
                    @close="handleClose"
                    background-color="#545c64"
                    text-color="#fff"
                    active-text-color="#ffd04b"
                    style="padding: 10px 0;">
                    <el-submenu index="1">
                        <template slot="title">
                            <i class="el-icon-location"></i>
                            <transition name="el-fade-in">
                                <span v-if="!isCollapse">用户信息</span>
                            </transition>
                        </template>
                        <el-menu-item index="1-1">用户信息</el-menu-item>
                    </el-submenu>
                    <el-submenu index="2">
                        <template slot="title">
                            <i class="el-icon-menu"></i>
                            <transition name="el-fade-in">
                                <span v-if="!isCollapse">员工信息</span>
                            </transition>
                        </template>
                        <el-menu-item index="2-1">员工信息</el-menu-item>
                    </el-submenu>
                </el-menu>
            </el-aside>

            <!-- 主体部分 -->
            <el-container>
                <!-- 头部区域 -->
                <el-header style="height: 60px; background-color: #888; color: white; display: flex; align-items: center; justify-content: space-between;">
                    <el-button
                        icon="el-icon-s-fold"
                        @click="toggleCollapse"
                        circle
                        style="background-color: transparent; color: white;">
                    </el-button>

                    <!-- 面包屑导航 -->
                    <el-breadcrumb separator-class="el-icon-arrow-right" style="margin-left: 20px;">
                        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                        <el-breadcrumb-item>活动管理</el-breadcrumb-item>
                        <el-breadcrumb-item>活动列表</el-breadcrumb-item>
                        <el-breadcrumb-item>活动详情</el-breadcrumb-item>
                    </el-breadcrumb>

                    <div style="flex: 1; width: 0; display: flex; align-items: center; justify-content: flex-end;">
                        <el-button icon="el-icon-full-screen" @click="toggleFullScreen" circle style="background-color: transparent; color: white; margin-right: 10px;"></el-button>
                        <i class="el-icon-guanping"></i>
                        <el-dropdown placement="bottom">
                            <div style="display: flex; align-items: center; cursor: default;">
                                <img src="@/assets/4.png" alt="" style="width: 40px; height: 40px;">
                                <span>管理员</span>
                            </div>
                            <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item @click="">个人信息</el-dropdown-item>
                                <el-dropdown-item @click="">修改密码</el-dropdown-item>
                                <el-dropdown-item @click="">退出登录</el-dropdown-item>
                            </el-dropdown-menu>
                        </el-dropdown>
                    </div>
                </el-header>

                <!-- 主体区域 -->
                <el-main>
                    <div style="display: flex; justify-content: center; align-items: center; position: relative; left: -290px;">
                        <el-input v-model="input" placeholder="请输入内容" style="width: 500px; margin-right: 10px;"></el-input>
                        <el-button @click="query" type="primary">查询</el-button>
                        <el-button @click="add" type="black">添加</el-button>
                    </div>

                    <el-table :data="paginatedData" height="calc(100vh - 160px)" style="width: 100%">
                        <el-table-column prop="username" label="用户名" width="180"></el-table-column>
                        <el-table-column prop="password" label="密码" width="180"></el-table-column>
                        <el-table-column prop="role" label="角色"></el-table-column>
                        <el-table-column label="操作" width="200">
                            <template slot-scope="scope">
                                <el-button @click="edit(scope.row)" type="primary" size="mini">修改</el-button>
                                <el-button @click="remove(scope.row)" type="danger" size="mini">删除</el-button>
                            </template>
                        </el-table-column>
                    </el-table>

                    <div style="margin-top: 20px; text-align: center;">
                        <el-button @click="prevPage" :disabled="currentPage === 1">上一页</el-button>
                        <span>第 {{ currentPage }} 页 / 共 {{ totalPages }} 页</span>
                        <el-button @click="nextPage" :disabled="currentPage === totalPages">下一页</el-button>
                    </div>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
import axios from 'axios';
import request from '../utils/request';

export default {
    name: 'App',
    data() {
        return {
            // 侧边栏
            asidWidth: '200px',
            isCollapse: false,

            // 主体区域
            input: '',
            userData: [],
            currentPage: 1,
            pageSize: 12
        };
    },
    mounted(){
        // 后台请求数据的接口
        axios.get('http://localhost:9999/users/list').then(res=>{
           console.log(res.data);
           this.userData=res.data;
        })
    },
    computed: {
        paginatedData() {
            const start = (this.currentPage - 1) * this.pageSize;
            return this.userData.slice(start, start + this.pageSize);
        },
        totalPages() {
            return Math.ceil(this.userData.length / this.pageSize);
        }
    },
    methods: {
        toggleCollapse() {
            this.isCollapse = !this.isCollapse;
        },
        query() {
            console.log('查询内容:', this.input);
            // 添加查询逻辑
        },
        nextPage() {
            if (this.currentPage < this.totalPages) {
                this.currentPage++;
            }
        },
        prevPage() {
            if (this.currentPage > 1) {
                this.currentPage--;
            }
        },
        edit(row) {
            console.log('修改行:', row);
        },
        remove(row) {
            console.log('删除行:', row);
        }
    }
}
</script>

<style>
.move-up {
  position: relative;
  top: -63px;
}

.el-fade-in-enter-active, .el-fade-in-leave-active {
    transition: opacity 0.5s;
}
.el-fade-in-enter, .el-fade-in-leave-to {
    opacity: 0;
}

.el-header {
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
}

.el-main {
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
    padding: 20px;
    overflow: auto; /* 确保内容可以滚动 */
}
</style>
