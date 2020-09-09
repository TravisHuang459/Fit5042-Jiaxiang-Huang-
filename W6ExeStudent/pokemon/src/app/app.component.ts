import {Component} from '@angular/core'; import {Router} from '@angular/router';

@Component({
  selector: 'app-root', // css selector that tells angular to inject the component wherever it finds the selector
  templateUrl: './app.component.html', // references the components html template to render
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Pokemon APP';
  constructor( private router: Router) {

  }
  onClick() {
    console.log('varnav')
    this.router.navigate(['/']);
  }
}
