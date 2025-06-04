import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {PoButtonModule} from '@po-ui/ng-components';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, PoButtonModule],
  standalone: true,
  template: '<router-outlet />'
})
export class AppComponent {
}
