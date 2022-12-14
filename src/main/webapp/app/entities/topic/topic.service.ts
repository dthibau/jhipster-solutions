import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { Topic } from './topic.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class TopicService {

    private resourceUrl = SERVER_API_URL + 'api/topics';

    constructor(private http: Http) { }

    create(topic: Topic): Observable<Topic> {
        const copy = this.convert(topic);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(topic: Topic): Observable<Topic> {
        const copy = this.convert(topic);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<Topic> {
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
     * Convert a returned JSON object to Topic.
     */
    private convertItemFromServer(json: any): Topic {
        const entity: Topic = Object.assign(new Topic(), json);
        return entity;
    }

    /**
     * Convert a Topic to a JSON which can be sent to the server.
     */
    private convert(topic: Topic): Topic {
        const copy: Topic = Object.assign({}, topic);
        return copy;
    }
}
