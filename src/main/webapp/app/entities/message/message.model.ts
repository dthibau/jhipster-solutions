import { BaseEntity, User } from './../../shared';

export class Message implements BaseEntity {
    constructor(
        public id?: number,
        public subject?: string,
        public content?: string,
        public postDate?: any,
        public responses?: BaseEntity[],
        public auteur?: User,
        public topic?: BaseEntity,
        public message?: BaseEntity,
    ) {
    }
}
