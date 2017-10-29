import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Topic } from './topic.model';
import { TopicPopupService } from './topic-popup.service';
import { TopicService } from './topic.service';

@Component({
    selector: 'jhi-topic-dialog',
    templateUrl: './topic-dialog.component.html'
})
export class TopicDialogComponent implements OnInit {

    topic: Topic;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private topicService: TopicService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.topic.id !== undefined) {
            this.subscribeToSaveResponse(
                this.topicService.update(this.topic));
        } else {
            this.subscribeToSaveResponse(
                this.topicService.create(this.topic));
        }
    }

    private subscribeToSaveResponse(result: Observable<Topic>) {
        result.subscribe((res: Topic) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Topic) {
        this.eventManager.broadcast({ name: 'topicListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }
}

@Component({
    selector: 'jhi-topic-popup',
    template: ''
})
export class TopicPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private topicPopupService: TopicPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.topicPopupService
                    .open(TopicDialogComponent as Component, params['id']);
            } else {
                this.topicPopupService
                    .open(TopicDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
