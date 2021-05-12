import axios from 'axios';
import { useEffect, useState } from 'react';
import Chart from 'react-apexcharts';
import { round } from 'types/format';
import { SaleSuccess } from 'types/sale';
import { BASE_URL } from 'Utils/request';

type SeriesData = {
    name: string;
    data: number[];
}

type ChartData = {
    labels: {
        categories: string[];

    };
    series: SeriesData[];
}


const BarChart = () => {

    const [ChartData, setChartData] = useState<ChartData>({
        
            labels: {
                categories: []
            },
            series: [
                {
                    name: "",
                    data: []                   
                }
            ]
    });

    useEffect(() => {  
        
        axios.get(`${BASE_URL}/sales/success-by-seller`)
        .then((response) => {
            const data = response.data as SaleSuccess[];
            const myLabels = data.map(x => x.sellerName);
            const mySeries = data.map(x => round(x.deals / x.visited, 1));

            setChartData({
        
                labels: {
                    categories: myLabels
                },
                series: [
                    {
                        name: "% Sucesso",
                        data: mySeries                   
                    }
                ]
              });
        });

    }, []);


    const options = {
       plotOptions: {
            bar: {
               horizontal: true,
            }
        },
    };
    
   // const mockData = {
     //   labels: {
    //        categories: ['Anakin', 'Barry Allen', 'Kal-El', 'Logan', 'Padmé']
     //   },
      //  series: [
      //      {
        //        name: "% Sucesso",
        //        data: [43.6, 67.1, 67.7, 45.6, 71.1]                   
        //    }
      //  ]
   // };

    return (
        <Chart
             options={{...options, xaxis: ChartData.labels}} 
             series={ChartData.series}
             type="bar"
             height="240"
        />
    );
  }
  
  export default BarChart;