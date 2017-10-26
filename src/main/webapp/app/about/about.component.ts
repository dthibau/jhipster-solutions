import { Component, OnInit } from '@angular/core';

import { VERSION } from '../app.constants';

@Component({
  selector: 'jhi-about',
  templateUrl: './about.component.html',
  styles: []
})
export class AboutComponent implements OnInit {

  version: string;

  constructor() {
    this.version = VERSION ? 'v' + VERSION : '';
  }

  ngOnInit() {
  }

}
