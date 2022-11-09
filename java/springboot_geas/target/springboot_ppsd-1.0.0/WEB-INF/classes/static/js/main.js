// 获取日期
function getDateString() {
    var d, s, yy, mm, dd;
    d = new Date();
    yy = d.getFullYear();
    mm = d.getMonth() + 1;
    dd = d.getDate();
    s = yy + "年" + mm + "月" + dd + "日";
    return s;
}

//高度自动填充
function autoFix() {
    let height = document.documentElement.clientHeight;
    //如果当前container高度小于浏览器窗口的高度则进行自动填充
    if ($(".container").height() <= height ){
        $(".container").height(height);
    }
}

$(function() {
    $("#mydate").html(getDateString());
    // 搜索操作
    $("#keyword").keyup(function() {
        var kw = $(this).val();
        $.ajax({
            url: 'https://sp0.baidu.com/5a1Fazu8AA54nxGko9WTAnF6hhy/su',
            jsonp: 'cb',
            data: {
                wd: kw
            },
            dataType: 'jsonp',
            success: function(data) {
                var sug = data.s;
                var tag = '<ul>';
                $.each(sug, function(i, e) {
                    tag += '<li>' + e + '</li>';
                });
                tag += '</ul>';
                $("#info").html(tag);
                $("#info").find('li').hover(function() {
                    $(this).css('backgroundColor', '#337AB7');
                }, function() {
                    $(this).css('backgroundColor', 'lightgray');
                });
                $("#info li").click(function() {
                    // var value=$(this).text();
                    // console.dir(value);
                    $("#keyword").val($(this).text());
                });
                //https://www.baidu.com/s?&word=sss
                $("#query").click(function() {
                    var search = "word=" + $("#keyword").val();
                    window.open("https://www.baidu.com/s?&" + search, "_blank");
                    // //页面刷新
                    // window.location.reload();
                });
            }
        });
    });
});

//查询属-物种的的多态性分布
function getSpeDist(SSRID) {
    $.ajax({
        type: "post",
        async: true,
        url: "../ssr/getSpeciesDistribute",//获取SSR分布情况
        data: 'ssrid=' + SSRID,
        dataType: "json",
        success: function (distributeTO) {
            var genus=distributeTO.genus;
            var genusID=genus+": "+SSRID;//属名+ssrid

            var params=new Array();
            for(var i=0;i<distributeTO.distField.length;i++){
                var distObj=new Object();
                distObj.name=distributeTO.distField[i];
                distObj.value=distributeTO.distrubute[i];
                params.push(distObj);
            }

            //console.log(distributeTO);
            var SSRChart = echarts.init(document.getElementById('SSRChart'));
            SSRChart.showLoading();
            var option={
                color: ['#61cc44'],
                tooltip: {
                    show: true,
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    },
                    formatter:"{a}<br/>{b}",
                },
                legend: {data: [genusID]},
                toolbox:{
                    show:true,
                    feature:{
                        mark:{
                            show:true//辅助线开关
                        },
                        dataView:{
                            show:true,
                            readOnly:true,
                            title:genusID,
                            lang:['dataview', 'close', 'flesh'],
                            optionToContent:function (opt) {
                                var axisData=opt.xAxis[0].data;//获取物种名
                                var genus=opt.series[0].name;//获取属名加id号
                                var distparam=opt.series[0].data;//获取param对象数组
                                var table='<table border="1" style="width: 100%;text-align: center;user-select: text"><tbody><tr><td colspan="3"><b>'+genus+'</b></td></tr>' +
                                    '<tr><td><b>species</b></td><td><b>repeats</b></td><td><b>areas</b></td>';
                                for(var i=0;i<axisData.length;i++){
                                    table+='<tr><td>'+axisData[i]+'</td><td>'+distparam[i].value+'</td><td>'+distparam[i].name+'</td></tr>';
                                }
                                table+='</tbody></table>';
                                return table;
                            }
                        },
                        restore:{
                            show:true
                        },
                        saveAsImage:{
                            show:true
                        }
                    }

                },
                xAxis: {
                    data: distributeTO.organizmName,
                    axisLabel : {//坐标轴刻度标签的相关设置。
                        interval:0,
                        rotate:"45"
                    }
                },
                yAxis: {},
                series: [{
                    name: genusID,
                    type: 'bar',
                    data: params,
                    //itemStyle: { normal: {color:function (value){return "#"+("00000"+((Math.random()*16777215+0.5)>>0).toString(16)).slice(-6); }},},
                    //barWidth: 30,
                }]
            };
            SSRChart.hideLoading();
            SSRChart.setOption(option);
        },

        error: function () {
            var xAxis = {
                data: []
            };
            var series = [{
                name: genusID,
                type: 'bar',
                data: []
            }];
            var legend = {
                data: [SSRID]
            };
            SSRChart.setOption({
                xAxis: xAxis,
                legend: legend,
                series: series
            });
            SSRChart.showLoading({
                text: 'No Find' //loading话术
            });
            SSRChart.hideLoading();
        }
    });
}


//获取物种-品种多态性分布
function getCulDist(SSRID) {
    $.ajax({
        type: "post",
        async: true,
        url: "../ssr/getCultivarDistribute",//获取品种SSR分布情况
        data: 'ssrid=' + SSRID,
        dataType: "json",
        success: function (distributeTO) {
           // console.log(distributeTO);
            var genus=distributeTO.genus;
            var specie=distributeTO.specie;
            var genusID=genus+" "+specie+": "+SSRID;//属名+物种名+ssrid

            var params=new Array();
            for(var i=0;i<distributeTO.distField.length;i++){
                var distObj=new Object();
                distObj.name=distributeTO.distField[i];
                distObj.value=distributeTO.distrubute[i];
                params.push(distObj);
            }

            //console.log(distributeTO);
            var SSRChart = echarts.init(document.getElementById('SSRChart'));
            SSRChart.showLoading();
            var option={
                color: ['#61cc44'],
                grid:{
                    bottom: '100px'
                },
                tooltip: {
                    show: true,
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    },
                    formatter:"{a}<br/>{b}",
                },
                legend: {data: [genusID]},
                toolbox:{
                    show:true,
                    feature:{
                        mark:{
                            show:true//辅助线开关
                        },
                        dataView:{
                            show:true,
                            readOnly:true,
                            title:genusID,
                            lang:['dataview', 'close', 'flesh'],
                            optionToContent:function (opt) {
                                var axisData=opt.xAxis[0].data;//获取物种名
                                var genus=opt.series[0].name;//获取属名加id号
                                var distparam=opt.series[0].data;//获取param对象数组
                                var table='<table border="1" style="width: 100%;text-align: center;user-select: text"><tbody><tr><td colspan="3"><b>'+genus+'</b></td></tr>' +
                                    '<tr><td><b>cultivar</b></td><td><b>repeats</b></td><td><b>areas</b></td>';
                                for(var i=0;i<axisData.length;i++){
                                    table+='<tr><td>'+axisData[i]+'</td><td>'+distparam[i].value+'</td><td>'+distparam[i].name+'</td></tr>';
                                }
                                table+='</tbody></table>';
                                return table;
                            }
                        },
                        restore:{
                            show:true
                        },
                        saveAsImage:{
                            show:true
                        }
                    }
                },
                xAxis: {
                    data: distributeTO.cultivarName,
                    axisLabel : {//坐标轴刻度标签的相关设置。
                        interval:0,
                        rotate:"45",
                    }
                },
                yAxis: {},
                series: [{
                    name: genusID,
                    type: 'bar',
                    data: params,
                    //itemStyle: { normal: {color:function (value){return "#"+("00000"+((Math.random()*16777215+0.5)>>0).toString(16)).slice(-6); }},},
                    //barWidth: 30,
                }]
            };
            SSRChart.hideLoading();
            SSRChart.setOption(option);
        },

        error: function () {
            var xAxis = {
                data: []
            };
            var series = [{
                name: genusID,
                type: 'bar',
                data: []
            }];
            var legend = {
                data: [SSRID]
            };
            SSRChart.setOption({
                xAxis: xAxis,
                legend: legend,
                series: series
            });
            SSRChart.showLoading({
                text: 'No Find' //loading话术
            });
            SSRChart.hideLoading();
        }
    });
}

//查询SSR引物
function queryPrimer(SSRID,isSpecies) {
    $.ajax({
        url: "../ssr/queryPrimer",
        async: true,
        type: "POST",
        data: {'ssrid':SSRID,'isSpecies':isSpecies},
        success: function (data) {
           // console.log(data[0].species)
            var contentFw = null;
            var contentRe = null;
            $("#primerShow").empty();//初始化模态框区域
            for (var i = 0; i < data.length; i++) {
                if (i == 0) {
                    contentFw = "<tr><td rowspan=\"6\">" + data[0].ssrid + "</td> " +
                        "<td>" + data[i].primerFw + "</td> <td>" + data[i].tmFw + "</td> <td>" + data[i].sizeFw + "</td> <td>" + data[i].identityFw + "</td> <td>" + data[i].coverageFw + "</td> " +
                        "<td rowspan=\"2\">" + data[i].produceSize + "</td> <td rowspan=\"2\">" + data[i].start + "</td> <td rowspan=\"2\">" + data[i].end + "</td></tr>";
                } else {
                    contentFw = "<tr> <td>" + data[i].primerFw + "</td> <td>" + data[i].tmFw + "</td> <td>" + data[i].sizeFw + "</td> <td>" + data[i].identityFw + "</td> <td>" + data[i].coverageFw + "</td>" +
                        "<td rowspan=\"2\">" + data[i].produceSize + "</td> <td rowspan=\"2\">" + data[i].start + "</td> <td rowspan=\"2\">" + data[i].end + "</td></tr>";
                }
                contentRe = "<tr> <td>" + data[i].primerRe + "</td> <td>" + data[i].tmRe + "</td> <td>" + data[i].sizeRe + "</td> <td>" + data[i].identityRe + "</td> <td>" + data[i].coverageRe + "</td> </tr>";
                var tbody = contentFw + contentRe;
                $("#primerShow").append(tbody);
                //设置模态框表头格式
                $("#modalTable th").css({
                    "vertical-align": "middle",
                    "text-align": "center"
                });
                //设置模态框表内容格式
                $("#primerShow td").css({
                    "vertical-align": "middle",
                    "text-align": "center",
                });
            }
        },
        datatype: "json"
    });
}

//下拉框中显示所有物种
function seeAll() {
    $("#organizmUl1").empty();
    $("#organizmUl2").empty();
    $("#organizmUl3").empty();
    $.ajax({
        type: "get",
        async: true,
        url: "./ssr/genusMap",//获取物种表
        dataType: "json",
        success: function (genusMap) {
            //将取回的数据分三列展示
            var rowcount=parseInt(genusMap.length/3); //假如能整除则每列的行数
            var remainder=genusMap.length%3; //不能整除时的余数，只有0,1或2
            if (remainder===2){
                for (let i = 0; i < rowcount+1; i++) {
                    $("#organizmUl1").append("<li><a href='info?scientificname="+genusMap[i]+"'>"+genusMap[i]+"</a></li>");
                }
                for (let i = rowcount+1; i < rowcount*2+2; i++) {
                    $("#organizmUl2").append("<li><a href='info?scientificname="+genusMap[i]+"'>"+genusMap[i]+"</a></li>");
                }
                for (let i = rowcount*2+2; i < rowcount*3+2; i++) {
                    $("#organizmUl3").append("<li><a href='info?scientificname="+genusMap[i]+"'>"+genusMap[i]+"</a></li>");
                }
            }else {
                for (let i = 0; i < rowcount+remainder; i++) {
                    $("#organizmUl1").append("<li><a href='info?scientificname="+genusMap[i]+"'>"+genusMap[i]+"</a></li>");
                }
                for (let i = rowcount+remainder; i < rowcount*2+remainder; i++) {
                    $("#organizmUl2").append("<li><a href='info?scientificname="+genusMap[i]+"'>"+genusMap[i]+"</a></li>");
                }
                for (let i = rowcount*2+remainder; i <rowcount*3+remainder; i++) {
                    $("#organizmUl3").append("<li><a href='info?scientificname="+genusMap[i]+"'>"+genusMap[i]+"</a></li>");
                }
            }
        }
    })
}

function queryHotSpecies() {
    //monoDiv
    //dicoDiv
    $("#monoDiv").empty();
    $("#dicoDiv").empty();
    $.ajax({
        type: "get",
        async: true,
        url: "./HotSpecies",//获取热点物种
        dataType: "json",
        success:function (data) {
            //console.log(data);
            var content;
            for (let i = 0; i < data.length; i++) {
                let speciesNum;
                let cultivarNum;
                let spsiteurl = '#';
                if (data[i].species){
                    speciesNum= data[i].species.split("|").length + 1; //加一是把作为参考的物种也算上
                }else{
                    speciesNum=0;
                }

                if (data[i].cultivars){
                    cultivarNum= data[i].cultivars.split("|").length;
                }else{
                    cultivarNum=0;
                }

                if (data[i].refcultivar){
                    cultivarNum = cultivarNum+1;
                }

                if (data[i].spsiteurl){
                    spsiteurl=data[i].spsiteurl;
                }

                if (data[i].angiosperm === 'Monocotyledons'){
                    content = "<div class=\"hot-species  text-center\">\n" +
                        "                            <a href='info?scientificname="+data[i].genus+" "+data[i].specie+"'><img class='hot-img' src='ppsdFile/plantImg/"+data[i].img+"'></a><br>\n" +
                        "                            <b>"+data[i].genus+" "+data[i].specie+"</b><br>\n";
                    if(data[i].sperecord){
                        content += "<a href='ssr/speciesSsr?genus="+data[i].genus+"' target='_blank'>"+speciesNum+" species,"+data[i].sperecord+" records</a><br>\n";
                    }
                    if (data[i].culrecord){
                        content += "<a href='ssr/cultivarSsr?genus="+data[i].genus+"&species="+data[i].specie+"' target='_blank'>"+cultivarNum+" cultivar,"+data[i].culrecord+" records</a><br>\n";
                    }
                    if (data[i].spgenotype)  {
                        content += "<a href='"+spsiteurl+"' target='_blank'>"+data[i].spgenotype+"<span><img class='icon-img' src='ppsdFile/iconImg/"+data[i].spsite+"'></span></a>\n"
                    }
                    content +=  "</div>";
                    $("#monoDiv").append(content)
                }else{
                    content = "<div class=\"hot-species  text-center\">\n" +
                        "                            <a href='info?scientificname="+data[i].genus+" "+data[i].specie+"'><img class='hot-img' src='ppsdFile/plantImg/"+data[i].img+"'></a><br>\n" +
                        "                            <b>"+data[i].genus+" "+data[i].specie+"</b><br>\n";
                    if(data[i].sperecord){
                        content += "<a href='ssr/speciesSsr?genus="+data[i].genus+"' target='_blank'>"+speciesNum+" species,"+data[i].sperecord+" records</a><br>\n";
                    }
                    if (data[i].culrecord){
                        content += "<a href='ssr/cultivarSsr?genus="+data[i].genus+"&species="+data[i].specie+"' target='_blank'>"+cultivarNum+" cultivar,"+data[i].culrecord+" records</a><br>\n";
                    }
                    if (data[i].spgenotype)  {
                        content += "<a href='"+spsiteurl+"' target='_blank'>"+data[i].spgenotype+"<span><img class='icon-img' src='ppsdFile/iconImg/"+data[i].spsite+"'></span></a>\n"
                    }
                    content +=  "</div>";
                    $("#dicoDiv").append(content)
                }
            }
        }
    });
}

//获得info页面的图表
function infoChart() {
    //物种水平上的SSR分布
    var speciesChart = echarts.init(document.getElementById("speciesSsrChart"));
    var cultivarChart = echarts.init(document.getElementById("cultivarSsrChart"));
    //物种间关系图
    var treeChart = echarts.init(document.getElementById("infotree"));

    speciesChart.showLoading();
    cultivarChart.showLoading();
    treeChart.showLoading();
    speciesOption=null;
    cultivarOption=null;
    treeoption = null;

    var scientificname = $("#scientificnameInput").val();
    $.ajax({
        type: "get",
        async: true,
        url: "./motifs",
        data: 'scientificname=' + scientificname,
        dataType: "json",
        success:function (data) {
            //取出物种水平上的SSR基序计数
            var speciesMotifs = new Array();
            var speciesCount = new Array();
            //有数据才画图
            if (data.speciesMotifs.length>0){
                for (let i = 0; i < data.speciesMotifs.length; i++) {
                    speciesMotifs.push(data.speciesMotifs[i].motif);
                    speciesCount.push(data.speciesMotifs[i].total);
                }
                speciesOption={
                    title: {
                        text:'Sorting of Different Motifs',
                        subtext:'In Species Level',
                        x:'center',
                        textStyle: { //主标题文本样式
                            fontSize: 18,
                        },
                        subtextStyle:{
                            fontSize: 16,
                            color: '#333333'
                        }
                    },
                    color: ['#44A3BB'],
                    tooltip: {
                        show:true,
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        },
                    },
                    xAxis: {
                        type: 'category',
                        data: speciesMotifs,
                        axisLabel:{
                            interval:0,
                            rotate:"45"
                        }
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: speciesCount,
                        type: 'bar',
                        //showBackground: true,

                    }]
                };
                if (speciesOption && typeof speciesOption==="object"){
                    speciesChart.hideLoading();
                    speciesChart.setOption(speciesOption,true);
                }
            }else{
                $("#speciesSsrChart").hide();
            }

            //取出品种水平上的SSR基序计数
          //  console.log(data.cultivarMotifs)
            var cultivarMotifs = new Array();
            var cultivarCount = new Array();
            if (data.cultivarMotifs.length>0){
                for (let i = 0; i < data.cultivarMotifs.length; i++) {
                    cultivarMotifs.push(data.cultivarMotifs[i].motif);
                    cultivarCount.push(data.cultivarMotifs[i].total);
                }
                cultivarOption={
                    title: {
                        text:'Sorting of Different Motifs',
                        subtext:'In Cultivar Level',
                        x:'center',
                        textStyle: { //主标题文本样式
                            fontSize: 18,
                        },
                        subtextStyle:{
                            fontSize: 16,
                            color: '#333333'
                        }
                    },
                    color: ['#44A3BB'],
                    tooltip: {
                        show:true,
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        },
                    },
                    xAxis: {
                        type: 'category',
                        data: cultivarMotifs,
                        axisLabel:{
                            interval:0,
                            rotate:"45"
                        }
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: cultivarCount,
                        type: 'bar',
                        //showBackground: true,

                    }]
                };
                if (cultivarOption && typeof cultivarOption==="object"){
                    cultivarChart.hideLoading();
                    cultivarChart.setOption(cultivarOption,true);
                }
            }else{
                $("#cultivarSsrChart").hide();
            }

            //取出物种关系树形图数据并渲染树形图
            var genusTree = {
                'name':data.genusFamily[0].genus,
                'label':{
                    'position':'left'
                }
            };
            var speciesArray = new Array();
            var speciesChild = [];

          //  console.log(data.genusFamily);
            if (data.genusFamily[0].species){ //查看是否有物种信息，用|隔开
                speciesArray = data.genusFamily[0].species.split("|");
            }
            speciesArray.unshift(data.genusFamily[0].ref); //将参考物种名加入
            for (let i = 0; i < speciesArray.length; i++) {
                speciesChild.push({'name':speciesArray[i]});
            }

            //遍历genusFamily集合
            for (let i = 0; i < data.genusFamily.length; i++) {
                if (data.genusFamily[i].specie){
                    //如果有品种级别的SSR，则遍历speciesChild，将品种数组加入到对应的物种对象中
                    let cultivarArray = data.genusFamily[i].cultivars.split("|");
                    let cultivarChild = [];
                    //将品种数组转为品种对象数组
                    if (data.genusFamily[i].refcultivar){
                        cultivarChild.push({'name':data.genusFamily[i].refcultivar}); //添加该物种的参考品种
                    }

                    for (let k = 0; k < cultivarArray.length; k++) {
                        cultivarChild.push({'name':cultivarArray[k]});
                    }
                    for (let j = 0; j < speciesChild.length; j++) {
                        //判断当前speciesChild的元素对象是否是需要加入品种信息的物种
                        if (speciesChild[j].name === data.genusFamily[i].specie){
                            speciesChild[j].children = cultivarChild;
                        }
                    }
                }
            }
            genusTree.children = speciesChild;
            //设置树形表
            treeoption = {
                tooltip: {
                    trigger: 'item',
                    triggerOn: 'mousemove'
                },
                series: [{
                    type: 'tree',
                    id: 0,
                    name: 'tree1',
                    data: [genusTree],
                    top: '5%',
                    left: '10%',
                    bottom: '8%',
                    right: '20%',
                    symbolSize: 8,
                    edgeShape: 'curve',
                    edgeForkPosition: '63%',
                    initialTreeDepth: 3,
                    lineStyle: {
                        width: 2,
                    },

                    label: {
                        position: 'right',
                        verticalAlign: 'middle',
                        //align: 'right',
                        fontSize:18,
                        fontStyle:'italic',
                        backgroundColor:'whitesmoke',
                        padding:[0,5,0,0]
                    },

                    leaves: {
                        label: {
                            position: 'right',
                            verticalAlign: 'middle',
                            // align: 'left'
                        }
                    },

                    expandAndCollapse: true,
                    animationDuration: 550,
                    animationDurationUpdate: 750
                }]
            };
            if (treeoption && typeof treeoption === "object") {
                treeChart.hideLoading();
                treeChart.setOption(treeoption, true);
            }
        }
    });
}

//为info页面绘制物种、品种SSR的饼图
function countSSRPie() {
    var data = new Array();
    var sperecord = $("#sperecord").val();
    var culrecord = $("#culrecord").val();
    var sciname = $("#scientificnameInput").val().split(/\s+/);
    if ((sperecord+culrecord)>0){
        if (sperecord > 0){
            data.push({'value':sperecord,'name':'species SSR','url': "ssr/speciesSsr?genus="+sciname[0]});
        }
        if (culrecord > 0){
            data.push({'value':culrecord,'name':'cultivar SSR','url': "ssr/cultivarSsr?genus="+sciname[0]+"&species="+sciname[1]});
        }

        var ssrChart = echarts.init(document.getElementById("ssrChart"));
        ssrChart.showLoading();
        option = null;
        option = {
            title: {
                text: 'SSR in Different Levels',
                subtext: 'species/cultivar',
                left: 'center',
                textStyle: { //主标题文本样式
                    fontSize: 18,
                },
                subtextStyle:{
                    fontSize: 16,
                    color: '#333333'
                }
            },
            tooltip: {
                trigger: 'item',
                formatter: '{a} <br/>{b} : {c} ({d}%)'
            },
            legend: {
                orient: 'vertical',
                right: '10%',
                top:'10%',
                data: ['species SSR', 'cultivar SSR']
            },
            color:['#44A3BB','#5DC4AF'],
            series: [
                {
                    name: 'Link to data',
                    type: 'pie',
                    radius: '70%',
                    center: ['50%', '55%'],
                    data: data,
                    label:{
                        fontWeight:'bold',
                        fontSize: 16,
                    },
                    labelLine:{
                        length:'20px',
                        length2: '30px',
                        lineStyle: {
                            width: 2
                        }
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
        if (option && typeof option === "object") {
            ssrChart.hideLoading();
            ssrChart.setOption(option, true);
        }
        // 饼图点击跳转到指定页面
        ssrChart.on('click', function (param) {
            window.open(param.data.url);
        });
    }
}
