import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { ForumTopicModule } from './topic/topic.module';
import { ForumMessageModule } from './message/message.module';
import { FluxModule } from './message/flux/flux.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        ForumTopicModule,
        ForumMessageModule,
        FluxModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ForumEntityModule {}
