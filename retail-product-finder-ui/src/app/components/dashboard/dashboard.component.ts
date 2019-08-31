import {Component, OnInit} from '@angular/core';
import {TokenService} from 'src/app/services/token/token.service';
//Not covered in Demo. 
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  title = 'Month wise Tenants Details';

  chartOptions = {
    responsive: true    // THIS WILL MAKE THE CHART RESPONSIVE (VISIBLE IN ANY DEVICE).
  };

  labels = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];

  // STATIC DATA FOR THE CHART IN JSON FORMAT.
  objChartData = [
    {
      label: 'D Mart',
      data: [21, 56, 4, 31, 45, 15, 57, 61, 9, 17, 24, 59]
    },
    {
      label: 'Reliance Mart',
      data: [47, 9, 28, 54, 77, 51, 24, 56, 58, 53, 35, 23]
    },
    {
      label: 'Real Mart',
      data: [21, 87, 4, 13, 64, 15, 57, 61, 29, 34, 24, 59]
    },
    {
      label: 'Big Bazaar',
      data: [24, 56, 77, 53, 35, 23, 9, 28, 54, 77, 51, 24]
    }
  ];

  // STATIC DATA FOR THE CHART IN JSON FORMAT.
  singularArrayChartData = [221, 56, 4, 31, 45, 15, 57, 61, 9, 17, 24, 59];

  constructor(private tokenService: TokenService) {
  }

  // CHART CLICK EVENT.
  onChartClick(event) {
    console.log(event);
  }

  ngOnInit() {
  }

}
