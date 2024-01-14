<template>
    <div style="padding: 10px">
        <el-table stripe :data="tableData">
            <el-table-column label="id" prop="id"/>
            <el-table-column label="订单名称" prop="name"/>
            <el-table-column label="订单编号" prop="orderId"/>
            <el-table-column label="支付宝订单号" prop="alipayNo"/>
            <el-table-column label="总价格" prop="total"/>
            <el-table-column label="创建时间" prop="createTime"/>
            <el-table-column label="支付时间" prop="payTime"/>
            <el-table-column label="订单状态" prop="state"/>
            <el-table-column label="操作">
                <template v-slot="scope">
                    <el-button @click="pay(scope.row)" type="primary" size="small" :disabled="scope.row.state">支付
                    </el-button>
                    <el-button @click="cancel(scope.row.id)" type="danger" size="small" :disabled="scope.row.state">取消
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    const baseUrl = "http://localhost:9090"
    export default {
        name: 'Orders',
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
                fetch(baseUrl + '/api/orders').then(res => res.json()).then(res => {
                    this.tableData = res
                })
            },
            pay(row) {
                window.open("http://localhost:9090/alipay/pay?subject=" + row.name + "&traceNo=" + row.orderId + "&totalAmount=" + row.total)
                this.$message.success("请求支付宝成功")
            },
            cancel(row) {
                fetch(baseUrl + '/api/orders').then(res => res.json()).then(res => {
                    this.tableData = res
                })
            },
        }
    }
</script>