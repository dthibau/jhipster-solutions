/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { ForumTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { TopicDetailComponent } from '../../../../../../main/webapp/app/entities/topic/topic-detail.component';
import { TopicService } from '../../../../../../main/webapp/app/entities/topic/topic.service';
import { Topic } from '../../../../../../main/webapp/app/entities/topic/topic.model';

describe('Component Tests', () => {

    describe('Topic Management Detail Component', () => {
        let comp: TopicDetailComponent;
        let fixture: ComponentFixture<TopicDetailComponent>;
        let service: TopicService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ForumTestModule],
                declarations: [TopicDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    TopicService,
                    JhiEventManager
                ]
            }).overrideTemplate(TopicDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TopicDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TopicService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Topic(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.topic).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
