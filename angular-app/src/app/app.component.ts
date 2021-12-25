import { ZbiriService } from './zbiri.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'angular-app';

  moha : any[] = [];

  constructor(public zbService: ZbiriService){}

  ngOnInit() {
    this.zbService.get().subscribe(res => {
      this.moha = res;
    })
  }

}
