import { BaseEntity } from './../../shared';

export class Topic implements BaseEntity {
    constructor(
        public id?: number,
        public title?: string,
        public description?: string,
    ) {
    }
}
