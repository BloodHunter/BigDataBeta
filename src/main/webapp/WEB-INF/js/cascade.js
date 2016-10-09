/**
 * Created by Simple_love on 2016/4/1.

 */
function Cascade(){
        this.item = {}
}
Cascade.prototype.add =function(id,iArray){
        this.item[id] = iArray;
}

Cascade.prototype.isExist = function(id){
        return typeof (this.item[id]) != "undefined";
}

function change(v){
        var key = "0";
        for(var i = 0; i < v;i++){
                key += ("_" + (document.getElementById(s[i]).selectedIndex));
        }
        var ss = document.getElementById(s[v]);
        with (ss){
                length = 0;
                options[0] = new Option(opt0[v].name,opt0[v].code);
                //console.log(v && document.getElementById(s[v-1]).selectedIndex > 0);
                //console.log(v && document.getElementById(s[v-1]).options[0].text != "不选择")
                var index = 0
                if(v>=1){
                        index = document.getElementById(s[v-1]).selectedIndex;
                }
                //console.log(!v)
                if(v && document.getElementById(s[v-1]).options[index].text != "不选择" || !v){
                        if(cascade.isExist(key)){
                                content = cascade.item[key];
                                for(var i = 0; i < content.length;i++){
                                        options[length] = new Option(content[i].name,content[i].code);
                                }
                                if(v)options[0].selected = true;
                        }
                }
                if(++v< s.length)
                        change(v);
        }
}

var cascade = new Cascade();

cascade.add("0",[{code:'600000', name:'信息科学'}, {code:'650000', name:'工程与技术'}, {code:'700000', name:'生物医药'}, {code:'750000', name:'农业科学'}, {code:'800000', name:'人文与社科'}, {code:'900000', name:'统计年鉴数据'}]);
cascade.add("0_0",[{code:'101000', name:'数学'},{code:'102000', name:'地球科学'},{code:'103000', name:'力学'},{code:'104000', name:'物理学'},{code:'105000', name:'化学'}, {code:'106000', name:'天文学'}]);
cascade.add("0_0_1",[{code:'101001', name:'数学史'}, {code:'101002', name:'数理逻辑与数学基础'}, {code:'101003', name:'数论'}]);
cascade.add("0_0_2",[{code:'102001', name:'地球科学史'}, {code:'102002', name:'大气科学'}, {code:'102003', name:'固体地球物理学'}])
cascade.add("0_0_3",[{code:'103001', name:'基础力学'}, {code:'103002', name:'固体力学'}, {code:'103003', name:'振动与波'}])
cascade.add("0_0_4",[{code:'104001', name:'物理学史'}, {code:'104002', name:'理论物理学'}, {code:'104003', name:'声学'}])
cascade.add("0_0_5",[{code:'105001', name:'化学史'}, {code:'105002', name:'无机化学'}, {code:'105003', name:'有机化学'}])
cascade.add("0_0_6",[{code:'106001', name:'天文学史'}, {code:'106002', name:'天体力学'}, {code:'106003', name:'天体物理学'}])
cascade.add("0_1",[{code:'601000', name:'电子与通信技术'}, {code:'602000', name:'计算机技术'}, {code:'603000', name:'信息与系统科学'}])
cascade.add("0_1_2",[{code:'602001', name:'机器学习'}, {code:'602002', name:'数据挖掘'}, {code:'602003', name:'人工智能'}, {code:'602004', name:'并行计算'}, {code:'602005', name:'信息检索'}, {code:'602006', name:'算法理论'}, {code:'602007', name:'数据库'}, {code:'602008', name:'计算机图形'}, {code:'602010', name:'自然语言处理'}, {code:'602011', name:'网络和通讯'}, {code:'602012', name:'操作系统'}, {code:'602014', name:'视频处理'}, {code:'602015', name:'程序设计语言'}, {code:'602016', name:'嵌入式设计'}, {code:'602017', name:'科学计算'}, {code:'602018', name:'安全与隐私'}, {code:'602019', name:'计算机仿真'}, {code:'602020', name:'软件工程'}, {code:'602025', name:'语音处理'}, {code:'602026', name:'图像处理'}])
cascade.add("0_1_3",[{code:'603001', name:'信息与系统科学基础学科'}, {code:'603002', name:'系统学'}, {code:'603003', name:'控制理论'}])
cascade.add("0_2",[{code:'651000', name:'工程与技术科学基础'}, {code:'652000', name:'测绘科学技术'}, {code:'653000', name:'材料科学'}])
cascade.add("0_3",[{code:'701000', name:'生物学'}, {code:'702000', name:'基础医学'}, {code:'703000', name:'临床医学'}])
cascade.add("0_4",[{code:'751000', name:'农学'}, {code:'752000', name:'林学'}, {code:'754000', name:'水产学'}])
cascade.add("0_5",[{code:'801000', name:'统计学'}, {code:'802000', name:'经济学'}, {code:'803000', name:'马克思主义'}])
cascade.add("0_6",[{code:'901000', name:'国民经济核算'}, {code:'902000', name:'固定资产投资'}, {code:'903000', name:'财政金融'}])
var s= ['first','second','third'];
var opt0 = [{name:"自然科学",code:"100000"},{name:"不选择",code:""},{name:"不选择",code:""}];
function init_cascade(){
        for(var i = 0; i < s.length; i++){
                document.getElementById(s[i]).onchange = new Function("change(" + (i+1) + ")");
        }
        change(0)
}
function init_select_grade(){
        var date = new Date();
        for(var i = 0; i <10;i++){
                var option = document.createElement("option");
                option.value = date.getFullYear()-i;
                option.innerHTML = option.value;
                document.getElementById("grade").appendChild(option)
        }
}