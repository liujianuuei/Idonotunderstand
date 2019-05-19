package BackPropagationNeuralNetwork;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //初始化神经网络的基本配置
        //第一个参数是一个整型数组，表示神经网络的层数和每层节点数，比如{3,10,10,10,10,2}表示输入层是3个节点，输出层是2个节点，中间有4层隐含层，每层10个节点
        //第二个参数是学习步长，第三个参数是动量系数
        BPNN bp = new BPNN(new int[]{5, 10, 3}, 0.12, 0.8);

        //设置样本数据，对应上面的4个二维坐标数据
        double[][] data = new double[][]{{0.100747, 0.00233442, 0.690329, 1.6875, 0.0483126}, {0.104574, 0.0029732, 0.660244, 1.71795, 0.0530419}, {0.159876, 0.016334, 0.710315, 2.91176, 0.0400851}, {0.128983, 0.00860048, 0.72265, 2.39355, 0.0454218}};
        //设置目标数据，对应4个坐标数据的分类
        double[][] target = new double[][]{{1, 0, 0}, {1, 0, 0}, {0, 0, 1}, {0, 0, 1}};

        //迭代训练5000次
        for (int n = 0; n < 5000; n++) {
            for (int i = 0; i < data.length; i++) {
                bp.train(data[i], target[i]);
            }
        }

        //根据训练结果来检验样本数据
        for (int j = 0; j < data.length; j++) {
            double[] result = bp.computeOut(data[j]);
            System.out.println(Arrays.toString(data[j]) + ":" + Arrays.toString(result));
        }

        //根据训练结果来预测一条新数据的分类
        //0.147046,0.00364964,0.67903,1.66344,0.0540356
        double[] x = new double[]{0.147046, 0.00364964, 0.67903, 1.66344, 0.0540356};
        double[] result = bp.computeOut(x);

        System.out.println(Arrays.toString(x) + ":" + Arrays.toString(result));
    }
}