<template>
    <div style="padding: 10px">
        <el-table stripe :data="tableData">
            <el-table-column label="id" prop="id"/>
            <el-table-column label="商品名称" prop="name"/>
            <el-table-column label="商品价格" prop="price"/>
            <el-table-column label="商品描述" prop="description"/>
            <el-table-column label="库存" prop="store"/>
            <el-table-column label="创建时间" prop="createTime"/>
            <el-table-column label="更新时间" prop="updateTime"/>
            <el-table-column label="单位" prop="unit"/>
            <el-table-column label="操作">
                <template v-slot="scope">
                    <el-button @click="buy(scope.row.id)" type="primary" size="small">购买</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    const baseUrl = "http://localhost:9090"
    export default {
        name: 'HomeView',
        data() {
            return {
                tableData: []
            }
        },
        created() {
            this.load()
        },
        methods: {
            load() {
                fetch(baseUrl + '/api/goods').then(res => res.json()).then(res => {
                    this.tableData = res
                })
            },
            buy(goodsId) {
                fetch(baseUrl + '/api/buy?goodsId=' + goodsId, {
                    headers: {
                        'Content-Type': 'application/json;charset=utf-8'
                    },
                    method: 'POST'
                }).then(res => res.json()).then(res => {
                    if (res) {
                        this.$message.success("下单成功")
                        this.load()
                    } else {
                        this.$message.error("下单失败")
                    }
                })
            }
        }
    }
</script>
