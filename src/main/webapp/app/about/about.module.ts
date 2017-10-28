import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ForumSharedModule } from '../shared';

import { AboutComponent, ABOUT_ROUTE } from './';

import {EditorModule} from 'primeng/primeng';

@NgModule({
  imports: [
  ForumSharedModule,
  EditorModule,
  RouterModule.forRoot([ ABOUT_ROUTE ], { useHash: true })
  ],
  declarations: [
  AboutComponent,
  ]
})
export class ForumAboutModule { }
