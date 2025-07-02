package desafio.itau.Springboot.dto;

import java.util.DoubleSummaryStatistics;

//Na classe da resposta da estatistica usar a biblioteca de double summary statistics

public class StatistcsResponse {
    private long count;
    private Double sum;
    private Double avg;
    private Double min;
    private Double max;

    public StatistcsResponse(DoubleSummaryStatistics estatistica){
        this.count = estatistica.getCount();
        this.sum = estatistica.getSum();
        this.avg = estatistica.getAverage();
        this.min = estatistica.getMin();
        this.max = estatistica.getMax();

        

    }

    public long getCount(){
        return count;
    }

    public Double getSum(){
        return sum;
    }

    public Double getAvg(){
        return avg;
    }

    public Double getMin(){
        return min;
    }

    public Double getMax(){
        return max;
    }


    
}
