import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ForumSharedModule } from '../shared';

import { AboutComponent, ABOUT_ROUTE } from './';

@NgModule({
  imports: [
  ForumSharedModule,
  RouterModule.forRoot([ ABOUT_ROUTE ], { useHash: true })
  ],
  declarations: [
  AboutComponent,
  ]
})
export class ForumAboutModule { }
