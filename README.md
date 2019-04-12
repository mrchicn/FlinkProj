# FlinkProj
Flink学习 FilterFunction()函数Filter算子
## Java操作Filter
        //Filter用例Java
        FilterFunction<String> dataFilter = new FilterFunction<String>() {
            public boolean filter(String value) throws Exception {
                if (value.contains("US")){
                    return true;
                }
                return false;
            }
        };
        result.filter(dataFilter).print();
## Scala操作Filter
    // Filter用例Scala
    data.filter(x=>x.contains("US")).print()


