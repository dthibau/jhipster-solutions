import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Message } from './message.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class MessageService {

    private resourceUrl = SERVER_API_URL + 'api/messages';
    private fluxUrl = SERVER_API_URL + 'api/flux';
    private fluxTreeUrl = SERVER_API_URL + 'api/flux-tree';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(message: Message): Observable<Message> {
        const copy = this.convert(message);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(message: Message): Observable<Message> {
        const copy = this.convert(message);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<Message> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    getFlux(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.fluxUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    getFluxTree(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.fluxTreeUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        const result = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            result.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return new ResponseWrapper(res.headers, result, res.status);
    }

    /**
     * Convert a returned JSON object to Message.
     */
    private convertItemFromServer(json: any): Message {
        const entity: Message = Object.assign(new Message(), json);
        entity.postDate = this.dateUtils
            .convertDateTimeFromServer(json.postDate);
        return entity;
    }

    /**
     * Convert a Message to a JSON which can be sent to the server.
     */
    private convert(message: Message): Message {
        const copy: Message = Object.assign({}, message);

        copy.postDate = this.dateUtils.toDate(message.postDate);
        return copy;
    }
}
