/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { ForumTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { MessageComponent } from '../../../../../../main/webapp/app/entities/message/message.component';
import { MessageService } from '../../../../../../main/webapp/app/entities/message/message.service';
import { Message } from '../../../../../../main/webapp/app/entities/message/message.model';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../../../../../main/webapp/app/shared';
import { MockPrincipal } from '../../../helpers/mock-principal.service';
import { PaginationConfig } from '../../../../../../main/webapp/app/blocks/config/uib-pagination.config'



describe('Component Tests', () => {

    describe('Message List Messages', () => {
        let comp: MessageComponent;
        let fixture: ComponentFixture<MessageComponent>;
        let service: MessageService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ForumTestModule],
                declarations: [MessageComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
		    PaginationConfig,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    MessageService,JhiEventManager, JhiParseLinks, JhiAlertService, Principal
                ]
            }).overrideTemplate(MessageComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            // fixture = TestBed.createComponent(MessageComponent);
            // comp = fixture.componentInstance;
            // service = fixture.debugElement.injector.get(MessageService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            // spyOn(service, 'find').and.returnValue(Observable.of(new Message(10)));

            // WHEN
            // comp.ngOnInit();

            // THEN
            // expect(service.getFlux).toHaveBeenCalledWith(123);
            // expect(comp.messages).toEqual(jasmine.objectContaining([{id: 10}]));
            });
        });
    });

});
