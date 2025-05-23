<template>
    <div >
      <div style="height: calc(100vh - 100px);">
      <div class="card"><h2 style="text-align: center">欢迎进入白领公寓管理系统后台管理中心</h2></div>

        <el-row :gutter="10" style="margin-top: 10px">
          <el-col :span="12"><div class="grid-content ep-bg-purple">
            <el-card shadow="always" style="height: 520px;">
              <div id="bie" style="width: 100%;height: 450px;padding-top: 20px;"></div>
            </el-card>
          </div>
          </el-col>
          <el-col :span="12"><div class="grid-content ep-bg-purple">
            <el-card shadow="always" style="height: 520px;">
              <div id="main" style="width: 100%;height: 450px;padding-top: 20px;"></div>
            </el-card>
          </div>
          </el-col>
        </el-row>


      </div>


<!--      <div style="margin-top: 50px">
        <el-button type="success"  plain>操作按钮</el-button>
      </div>
      <div style="margin-top: 50px">
        <el-input v-model="data.name" style="width: 200px;"/>
        <RouterLink to="/manager/test">跳转至test路由</RouterLink>
        <el-button @click="router.push('/manager/test?id=1')">通过push跳转</el-button>
      </div>-->

    </div>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import request from "@/utils/request.js";
import * as echarts from 'echarts';
import qs from "qs";
import B4I3EM2f from '@/assets/imgs/B4I3EM2f.png'
import dd from '@/assets/imgs/dd.png'
import update1 from '@/assets/imgs/update1.png'
import wffVGXii from '@/assets/imgs/wffVGXii.png'
onMounted(() => {

//饼状图
  const chartbie = echarts.init(document.getElementById("bie"));
  const option1 = {
    title: {
      text: '公寓分类统计',
      subtext: '数据比例统计',
      left: 'center'
    },
    tooltip: {
      trigger: 'item'
    },
    legend: {
      orient: 'vertical',
      left: 'left'
    },
    series: [
      {
        name: '公寓分类统计',
        type: 'pie',
        radius: '50%',
        /*     data: [ { value: 1048, name: 'Search Engine' }, ],*/
        label: {
          show: true, // 显示标签
          //position: 'inside', // 在饼图内部显示标签
          formatter: '{b}: {d}%' // 显示百分比
        },
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
          }
        }
      }
    ]
  };

  //向后端发起请求获取数据
  request.post('bie',qs.stringify({
  })).then(res=>{
    option1.series[0].data = res.data
    chartbie.setOption(option1);
  })

//折线图{费用统计}
  const chartmain = echarts.init(document.getElementById("main"));
  const option2 = {
    title: {
      text: '费用统计',
      subtext: '统计维度：每日',
      left: 'center'
    },
    xAxis: {
      type: 'category',
      //data: this.xmaindata
    },
    yAxis: {
      type: 'value'
    },
    /*    series: [
          {
            data: this.ymaindata,
            type: 'line',
            smooth: true
          }
        ]*/
    series: [
      {
        name:'费用统计',
        type:'line',
        smooth: true,
        //data:this.ymaindata,
        markPoint: {
          data: [
            {type: 'max', name: '最大金额'},
            {type: 'min', name: '最小金额'}
          ]
        },
        markLine: {
          data: [
            {type: 'average', name: '平均金额'}
          ]
        }
      }
    ]
  };

  //向后端发起请求获取数据
  request.post('tjMoney',qs.stringify({
  })).then(res=>{
    option2.xAxis.data = res.data.xmaindata
    option2.series[0].data = res.data.ymaindata
    chartmain.setOption(option2);
  })


});

</script>
