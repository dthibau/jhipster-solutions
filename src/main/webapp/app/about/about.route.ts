import { Route } from '@angular/router';

import { UserRouteAccessService } from '../shared';
import { AboutComponent } from './';

export const ABOUT_ROUTE: Route = {
    path: 'about',
    component: AboutComponent,
    data: {
        authorities: [],
        pageTitle: 'home.title'
    }
};
