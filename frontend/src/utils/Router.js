import Vue from 'vue';
import VueRouter from 'vue-router';
import SearchPage from "@/pages/SearchPage";
import EntryPage from "@/pages/EntryPage";
import ResultPage from "@/pages/ResultPage";
import UserPage from "@/pages/UserPage";

Vue.use(VueRouter);

const routes = [
    {
        path: '/index',
        component: SearchPage
    },
    {
        path: '/',
        redirect: '/index'
    },
    {
        path: '/entry/:entryName',
        component: EntryPage
    },
    {
        path: '/result/:keyword',
        component: ResultPage
    },
    {
        path: '/user/:userId',
        component: UserPage
    }
];

export const router = new VueRouter({
    routes: routes
});
