import {Component} from '@angular/core';
import {Event, Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'retail-product-finder-ui';

  constructor(private _router: Router) {
    this._router.events.subscribe((event: Event) => {
    });
  }
}
